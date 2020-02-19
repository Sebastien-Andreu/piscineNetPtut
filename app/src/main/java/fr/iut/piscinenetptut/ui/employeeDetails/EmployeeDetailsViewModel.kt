package fr.iut.piscinenetptut.ui.employeeDetails

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Employee
import fr.iut.piscinenetptut.entities.EmployeeSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.lang.Exception

class EmployeeDetailsViewModel {

    private val TAG: String = "EmployeeDetailsViewModel"
    private val json = Json(JsonConfiguration.Stable)
    private val requestHttp = httpRequest()


    fun showDetailOfEmployee(root: View){
        try {

            println(EmployeeSelected.employee)

            Fuel.get(requestHttp.url + "Picture/${EmployeeSelected.employee.picture}")
                .response { _, _, result ->
                    val (bytes, _) = result
                    if (bytes != null) {
                        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        root.findViewById<ImageView>(R.id.detailEmployeePicture)?.let {
                            it.setImageBitmap(bitmap)
                            it.visibility = View.VISIBLE
                        }
                    }
                }

            Fuel.get(requestHttp.url+"Employee/" + EmployeeSelected.employee.ID)
                .responseString { _, _, result ->
                    result.fold({ d ->

                        EmployeeSelected.employee = json.parse(Employee.serializer(), d)
                        val employee = EmployeeSelected.employee

                        root.findViewById<TextView>(R.id.detailEmployeeName).text = employee.name
                        root.findViewById<TextView>(R.id.detailEmployeeSurname).text = employee.surname
                        root.findViewById<TextView>(R.id.detailEmployeeMail).text = employee.mail
                        root.findViewById<TextView>(R.id.detailEmployeeAddr).text = employee.address
                        root.findViewById<TextView>(R.id.detailEmployeeTel).text = employee.telPhoneNumber
                    }, { err ->
                        println(err.message)
                    })
                }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}