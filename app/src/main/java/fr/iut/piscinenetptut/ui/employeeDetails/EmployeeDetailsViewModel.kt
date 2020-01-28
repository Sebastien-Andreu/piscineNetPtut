package fr.iut.piscinenetptut.ui.employeeDetails

import android.view.View
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

    /**
    *   Show employee details in the view
    *   @param View The view to show the details in
    **/
    fun showDetailOfEmployee(root: View){
        try {

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