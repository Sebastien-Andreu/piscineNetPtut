package fr.iut.piscinenetptut.ui.addCustomer

import fr.iut.piscinenetptut.entities.Customer

interface AddCustomerActivityMvc {
    fun onCustomerInformationIsLoaded(customer: Customer)
    interface listeners {
        fun onUserWantToAddNewCustomer()
    }
}