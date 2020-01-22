package fr.iut.piscinenetptut.ui.customerdetails.customer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CustomerDetailFragment : Fragment() {

    private lateinit var customerDetailFragmentMvcImpl: CustomerDetailFragmentMvcImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customerDetailFragmentMvcImpl = CustomerDetailFragmentMvcImpl(inflater.context, this)
        return customerDetailFragmentMvcImpl.root
    }

    override fun onResume() {
        super.onResume()
        customerDetailFragmentMvcImpl.onUserWantToShowDetailCustomer()
    }
}