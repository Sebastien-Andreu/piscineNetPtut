package fr.iut.piscinenetptut.ui.managementEmployee

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Employee
import fr.iut.piscinenetptut.entities.EmployeeSelected
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.isEmail
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.mail.SendMailCreateNewCustomer
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import kotlin.math.floor

class ManagementEmployeeMvcImpl (
    val managementEmployeeActivity: ManagementEmployeeActivity,
    val context: Context
): ManagementEmployeeMvc {

    val TAG: String = "ManagementEmployeeMvcImpl"

    private val json = Json(JsonConfiguration.Stable)
    private val requestHttp = httpRequest()

    var root: View? = null

    lateinit var employee: Employee

    init {
        try {
            root = View.inflate(context, R.layout.activity_add_employee, null)

            if (null != root) {

                root?.findViewById<Button>(R.id.addEmployeeButton)?.setOnClickListener {
                    if (verifyIfAllInputTextAreNotEmpty()){
                        managementEmployeeActivity.onUserWantToAddEmployee()
                    }
                }
                root?.findViewById<EditText>(R.id.addEmployeeMail)?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

                    override fun afterTextChanged(editable: Editable) {
                        if (!root?.findViewById<EditText>(R.id.addEmployeeMail)?.text.toString().isEmail()) {
                            root?.findViewById<EditText>(R.id.addEmployeeMail)?.setTextColor(Color.RED)
                        } else {
                            root?.findViewById<EditText>(R.id.addEmployeeMail)?.setTextColor(Color.BLACK)
                        }
                    }
                })
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    private fun verifyIfAllInputTextAreNotEmpty(): Boolean{
        return ( !root?.findViewById<TextView>(R.id.addEmployeeName)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addEmployeeSurname)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addEmployeeAddr)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addEmployeeMail)?.text.isNullOrEmpty() &&
                  root?.findViewById<EditText>(R.id.addEmployeeMail)?.text.toString().isEmail() &&
                 !root?.findViewById<TextView>(R.id.addEmployeeTelPhoneNumber)?.text.isNullOrEmpty())
    }

    override fun addEmployee(employee: Employee) {
        try {
            if (null != root) {
                this.employee = employee
                Fuel.post(requestHttp.url+"Employee")
                    .body(requestHttp.convertData(json.stringify(Employee.serializer(), employee)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({ d ->
                            createLoginForEmployee((employee.surname.toString()[0]) + "." + employee.name)
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun updateEmployee() {
        try {
            if (null != root) {
                Fuel.put(requestHttp.url+"Employee/" + EmployeeSelected.employee.ID)
                    .body(requestHttp.convertData(json.stringify(Employee.serializer(), EmployeeSelected.employee)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({
                            Toast.makeText(managementEmployeeActivity, "Updated !", Toast.LENGTH_LONG).show()
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }


    override fun createLoginForEmployee(login: String) {
        Fuel.get(requestHttp.url+"Register")
            .responseString { _, _, result ->
                result.fold({ d ->
                    verifyValidifyOfLogin(json.parse(Register.serializer().list,d), login)
                }, { err ->
                    println(err.message)
                })
            }
    }

    private fun verifyValidifyOfLogin(listRegister: List<Register>, login: String){
        var validityOfLogin = true

        listRegister.forEach {
            if (it.login == login){
                validityOfLogin = false
            }
        }
        if (validityOfLogin){
            val register = Register (
                login = login,
                mail = employee.mail,
                role = "employee"
            )
            val mail = SendMailCreateNewCustomer(register)
            mail.send(managementEmployeeActivity)
        } else {
            verifyValidifyOfLogin(listRegister, login + getAleaNumber())
        }
    }

    private fun getAleaNumber(): String{
        val chars = "0123456789"
        var passWord = ""
        for (i in 0..2) {
            passWord += chars[floor(Math.random() * chars.length).toInt()]
        }
        return passWord
    }
}