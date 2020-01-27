package fr.iut.piscinenetptut.ui.managementEmployee

import fr.iut.piscinenetptut.entities.Employee

interface ManagementEmployeeMvc {
    fun addEmployee(employee: Employee)
    fun updateEmployee()

    fun createLoginForEmployee(login: String)

    interface Listeners {
        fun onUserWantToAddEmployee()
        fun OnUserWantToModifyEmployeeInformation()
    }
}