package fr.iut.piscinenetptut.ui.managementEmployee

import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Employee
import fr.iut.piscinenetptut.entities.EmployeeSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor

class ManagementEmployeeViewModel {

    val TAG: String = "ManagementEmployeeViewModel"

    val addEmployeeCallBack: MutableLiveData<Employee> = MutableLiveData()
    val updateEmployeeCallBack: MutableLiveData<Employee> = MutableLiveData()

    fun onNeedToGetEmployeeInformation(root: View, id : Int? = EmployeeSelected.employee.ID) {
        try {
            val employee = Employee (
                ID = id,
                name = root.findViewById<EditText>(R.id.addEmployeeName)?.text.toString(),
                surname = root.findViewById<EditText>(R.id.addEmployeeSurname)?.text.toString(),
                mail = root.findViewById<EditText>(R.id.addEmployeeMail)?.text.toString(),
                address = root.findViewById<EditText>(R.id.addEmployeeAddr)?.text.toString(),
                telPhoneNumber = root.findViewById<EditText>(R.id.addEmployeeTelPhoneNumber)?.text.toString()
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
}