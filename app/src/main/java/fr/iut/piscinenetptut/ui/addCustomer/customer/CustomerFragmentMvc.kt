package fr.iut.piscinenetptut.ui.addCustomer.customer

interface CustomerFragmentMvc {

    fun verifyAllInput(): Boolean

    interface Listener {
        fun onUserWantToVerifyAlInput(): Boolean
    }
}