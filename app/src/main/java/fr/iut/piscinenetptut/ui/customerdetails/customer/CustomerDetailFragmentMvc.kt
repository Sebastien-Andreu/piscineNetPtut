package fr.iut.piscinenetptut.ui.customerdetails.customer

import fr.iut.piscinenetptut.entities.Customer

interface CustomerDetailFragmentMvc{

    fun onUserWantToShowDetailCustomer(customer: Customer)
    interface Listener {
    }
}