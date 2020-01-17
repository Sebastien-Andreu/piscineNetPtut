package fr.iut.piscinenetptut.ui.managementCustomer.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CustomerFragment : Fragment() , CustomerFragmentMvc.Listener {

    lateinit var customerFragmentMvcImpl: CustomerFragmentMvcImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customerFragmentMvcImpl = CustomerFragmentMvcImpl(inflater.context, this)
        return customerFragmentMvcImpl.root
    }

    override fun onUserWantToVerifyAlInput(): Boolean {
        return customerFragmentMvcImpl.verifyAllInput()
    }
}