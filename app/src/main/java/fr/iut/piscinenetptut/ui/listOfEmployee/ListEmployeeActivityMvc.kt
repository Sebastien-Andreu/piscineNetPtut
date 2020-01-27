package fr.iut.piscinenetptut.ui.listOfEmployee

import fr.iut.piscinenetptut.entities.Employee

interface ListEmployeeActivityMvc {


    fun onEmployeeListLoaded(employees: List<Employee>)
    fun verifyIfUpdateDataBase()

    interface Listeners {
        fun onUserTouchUserPreview(id: Int)
    }
}