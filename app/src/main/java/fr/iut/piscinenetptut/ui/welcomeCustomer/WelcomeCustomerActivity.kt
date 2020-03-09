package fr.iut.piscinenetptut.ui.welcomeCustomer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class WelcomeCustomerActivity: Fragment(), WelcomeCustomerMvc.Listener {

    val TAG: String = "WelcomeCustomerActivity"

    lateinit var welcomeCustomerMvcImpl: WelcomeCustomerMvcImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        welcomeCustomerMvcImpl = WelcomeCustomerMvcImpl(inflater.context, this)
        return welcomeCustomerMvcImpl.root
    }
}