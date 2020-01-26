package fr.iut.piscinenetptut.ui.managementCustomer

import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool

interface ManagementCustomerActivityMvc {
    fun addCustomer(customer: Customer)
    fun addPool(pool: Pool)
    fun updateCustomer()
    fun updatePool()
    fun createLoginForCustomer(login: String)
    interface Listeners {
        fun onUserWantToAddNewCustomer()
        fun onUserWantToAddNewPool(id_Customer: Int?= null)
        fun onUserWantToUpdateCustomer()
        fun onUserWantToUpdatePool()
    }
}