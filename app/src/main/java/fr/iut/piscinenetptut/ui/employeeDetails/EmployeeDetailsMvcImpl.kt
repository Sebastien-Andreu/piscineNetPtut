package fr.iut.piscinenetptut.ui.employeeDetails

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Account
import fr.iut.piscinenetptut.entities.EmployeeSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest

class EmployeeDetailsMvcImpl (
    private val context: Context,
    private val employeeDetailsActivity: EmployeeDetailsActivity
): EmployeeDetailsMvc {

    val TAG: String = "EmployeeDetailsMvcImpl"

    private val requestHttp = httpRequest()

    private lateinit var employeeDetailsViewModel: EmployeeDetailsViewModel

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.details_employee, null)

            employeeDetailsViewModel = EmployeeDetailsViewModel()

            root?.findViewById<Button>(R.id.updateEmployee)?.setOnClickListener{
                employeeDetailsActivity.onUserWantToUpdateEmployee()
            }

            root?.findViewById<Button>(R.id.deleteEmployee)?.setOnClickListener{
                val builder = employeeDetailsActivity.let { it1 -> AlertDialog.Builder(it1) }

                builder.setTitle(context.getString(R.string.DeleteCustomer))
                builder.setMessage(context.getString(R.string.EnterAdminPass))
                val input = EditText(employeeDetailsActivity)
                val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

                input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                input.layoutParams = lp
                builder.setView(input)

                builder.setPositiveButton(context.getString(R.string.yes)){_, _ ->
                    if (input.text.toString() == Account.register.password.toString()){
                        onUserWantToRemoveEmployee()
                    } else {
                        Toast.makeText(employeeDetailsActivity,context.getString(R.string.MauvaisMdp),Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setNegativeButton(context.getString(R.string.no)){_,_ -> }
                builder.setNeutralButton(context.getString(R.string.Cancel)){_,_ -> }

                val dialog: AlertDialog? = builder.create()
                dialog?.show()
            }

            onUserWantToShowDetailEmployee()
        } catch (exception : Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToShowDetailEmployee(){
        employeeDetailsViewModel.showDetailOfEmployee(root!!)
    }

    override fun onUserWantToRemoveEmployee() {
        Fuel.post(requestHttp.url+"Employee/Remove/" + EmployeeSelected.employee.ID)
            .header("Content-Type" to "application/x-www-form-urlencoded")
            .responseString { _, _, result ->
                result.fold({
                    Toast.makeText(employeeDetailsActivity,"Deleted !",Toast.LENGTH_SHORT).show()
                    employeeDetailsActivity.onBackPressed()
                }, { err ->
                    println(err.message)
                })
            }
    }
}