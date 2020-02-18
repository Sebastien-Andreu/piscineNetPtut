package fr.iut.piscinenetptut.ui.managementEmployee

import android.content.Intent
import fr.iut.piscinenetptut.entities.Employee

interface ManagementEmployeeMvc {
    fun addEmployee(employee: Employee)
    fun updateEmployee()

    fun onUserWantToShowDetailEmployeeToUpdate()
    fun createLoginForEmployee(login: String)
    fun showPicture(data: Intent?)

    interface Listeners {
        fun onUserWantToAddEmployee()
        fun onUserWantToModifyEmployeeInformation()

    }
}