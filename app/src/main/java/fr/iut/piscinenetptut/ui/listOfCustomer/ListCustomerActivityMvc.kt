package fr.iut.piscinenetptut.ui.listOfCustomer

import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool

interface ListCustomerActivityMvc {


    fun onUserListLoaded(customers: List<Customer>, pools: List<Pool>)
    fun verifyIfUpdateDataBase()

    interface Listeners {
        fun onUserTouchUserPreview(id: Int)
    }
}