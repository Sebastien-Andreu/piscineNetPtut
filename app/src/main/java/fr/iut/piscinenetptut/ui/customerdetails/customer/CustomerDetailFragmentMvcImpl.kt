package fr.iut.piscinenetptut.ui.customerdetails.customer

import android.content.Context
import android.view.View
import android.widget.Button
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
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

    lateinit var customerDetailFragmentViewModel: CustomerDetailFragmentViewModel

    init {
        try {
            root = View.inflate(context, R.layout.fragment_customer, null)

            customerDetailFragmentViewModel = CustomerDetailFragmentViewModel()

            root?.findViewById<Button>(R.id.detailCustomerMakeVisitButton)?.setOnClickListener {
                this@CustomerDetailFragmentMvcImpl.customerDetailFragment.activity!!.finish()
                this.context.let { WorkingMethodActivity.start(it, (customerDetailFragment.activity as CustomerDetailsActivity).register) }
            }

            root?.findViewById<Button>(R.id.updateCustomer)?.setOnClickListener{
                (customerDetailFragment.activity as CustomerDetailsActivity).onUserWantToUpdateCustomer()
            }

            onUserWantToShowDetailCustomer((customerDetailFragment.activity as CustomerDetailsActivity).customer)
        } catch (exception : Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToShowDetailCustomer(customer: Customer){
        customerDetailFragmentViewModel.showDetailOfCustomer(root!!, customer)
    }
}
