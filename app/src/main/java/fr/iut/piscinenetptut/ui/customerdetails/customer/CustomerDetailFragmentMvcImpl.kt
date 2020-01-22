package fr.iut.piscinenetptut.ui.customerdetails.customer

import android.content.Context
import android.view.View
import android.widget.Button
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.customerdetails.CustomerDetailsActivity
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity
import java.lang.Exception

class CustomerDetailFragmentMvcImpl (
    private val context: Context,
    private val customerDetailFragment: CustomerDetailFragment
    ): CustomerDetailFragmentMvc {

    private val TAG: String = "CustomerDetailFragmentMvcImpl"

    var root: View? = null

    private lateinit var customerDetailFragmentViewModel: CustomerDetailFragmentViewModel

    init {
        try {
            root = View.inflate(context, R.layout.fragment_customer, null)

            customerDetailFragmentViewModel = CustomerDetailFragmentViewModel()

            root?.findViewById<Button>(R.id.detailCustomerMakeVisitButton)?.setOnClickListener {
                this@CustomerDetailFragmentMvcImpl.customerDetailFragment.activity!!.finish()
                this.context.let { WorkingMethodActivity.start(it)}
            }

            root?.findViewById<Button>(R.id.updateCustomer)?.setOnClickListener{
                (customerDetailFragment.activity as CustomerDetailsActivity).onUserWantToUpdateCustomer()
            }

            onUserWantToShowDetailCustomer()
        } catch (exception : Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToShowDetailCustomer(){
        customerDetailFragmentViewModel.showDetailOfCustomer(root!!)
    }
}
