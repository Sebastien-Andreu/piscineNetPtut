package fr.iut.piscinenetptut.ui.managementCustomer

import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool

interface ManagementCustomerActivityMvc {
    fun onCustomerInformationIsLoaded(customer: Customer)
    fun onPoolInformationIsLoaded(pool: Pool)
    interface listeners {
        fun onUserWantToAddNewCustomer()
        fun onUserWantToAddNewPool(id_Customer: Int?)
    }
}