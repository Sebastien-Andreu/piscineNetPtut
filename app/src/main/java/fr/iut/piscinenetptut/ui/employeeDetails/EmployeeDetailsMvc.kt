package fr.iut.piscinenetptut.ui.employeeDetails

interface EmployeeDetailsMvc {

    fun onUserWantToShowDetailEmployee()
    fun onUserWantToRemoveEmployee()

    interface Listeners {
        fun onUserWantToUpdateEmployee()
    }
}