package fr.iut.piscinenetptut.ui.managementEmployee

import android.graphics.BitmapFactory
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Employee
import fr.iut.piscinenetptut.entities.EmployeeSelected
import fr.iut.piscinenetptut.library.extension.createUniqueIdV4
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest

class ManagementEmployeeViewModel {

    val TAG: String = "ManagementEmployeeViewModel"

    val addEmployeeCallBack: MutableLiveData<Employee> = MutableLiveData()
    val updateEmployeeCallBack: MutableLiveData<Employee> = MutableLiveData()

    private val requestHttp = httpRequest()

    fun onNeedToGetEmployeeInformation(root: View, id : Int? = EmployeeSelected.employee.ID, picture: String?= "picture" + createUniqueIdV4() + ".jpg") {
        try {
            val employee = Employee (
                ID = id,
                name = root.findViewById<EditText>(R.id.addEmployeeName)?.text.toString(),
                surname = root.findViewById<EditText>(R.id.addEmployeeSurname)?.text.toString(),
                mail = root.findViewById<EditText>(R.id.addEmployeeMail)?.text.toString(),
                address = root.findViewById<EditText>(R.id.addEmployeeAddr)?.text.toString(),
                telPhoneNumber = root.findViewById<EditText>(R.id.addEmployeeTelPhoneNumber)?.text.toString(),
                picture = picture
            )
            if (id != null){
                EmployeeSelected.employee = employee
                updateEmployeeCallBack.postValue(employee)
            } else {
                addEmployeeCallBack.postValue(employee)
            }
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    fun showInformationOfEmployeeWhenUserWantToUpdate(root: View) {
        try {
            val employee = EmployeeSelected.employee
            println(EmployeeSelected.employee)

            root.findViewById<EditText>(R.id.addEmployeeName)?.setText(employee.name)
            root.findViewById<EditText>(R.id.addEmployeeSurname)?.setText(employee.surname)
            root.findViewById<EditText>(R.id.addEmployeeMail)?.setText(employee.mail)
            root.findViewById<EditText>(R.id.addEmployeeAddr)?.setText(employee.address)
            root.findViewById<EditText>(R.id.addEmployeeTelPhoneNumber)?.setText(employee.telPhoneNumber.toString())

            Fuel.get(requestHttp.url + "Employee/${employee.picture}")
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
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}