package fr.iut.piscinenetptut.ui.managementCustomer.customer

interface CustomerFragmentMvc {

    fun verifyAllInput(): Boolean

    fun onUserWantToShowDetailCustomerToUpdate()


    interface Listener {
        fun onUserWantToVerifyAlInput(): Boolean
    }
}