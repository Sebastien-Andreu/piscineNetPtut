package fr.iut.piscinenetptut.ui.customerdetails.customer

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Account
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.ui.customerdetails.CustomerDetailsActivity
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity


class CustomerDetailFragmentMvcImpl (
    private val context: Context,
    private val customerDetailFragment: CustomerDetailFragment
    ): CustomerDetailFragmentMvc {

    private val TAG: String = "CustomerDetailFragmentMvcImpl"

    private val requestHttp = httpRequest()

    var root: View? = null

    private lateinit var customerDetailFragmentViewModel: CustomerDetailFragmentViewModel

    init {
        try {
            root = View.inflate(context, R.layout.fragment_customer, null)

            customerDetailFragmentViewModel = CustomerDetailFragmentViewModel()

            if( Account.register.role == "employee" || Account.register.role == "customer"){
                root?.findViewById<Button>(R.id.updateCustomer)?.visibility = View.GONE
                root?.findViewById<Button>(R.id.deleteCustomer)?.visibility = View.GONE
            }
            if (Account.register.role == "customer"){
                root?.findViewById<Button>(R.id.detailCustomerMakeVisitButton)?.visibility = View.GONE
            }

            root?.findViewById<Button>(R.id.detailCustomerMakeVisitButton)?.setOnClickListener {
                this@CustomerDetailFragmentMvcImpl.customerDetailFragment.activity!!.finish()
                this.context.let { WorkingMethodActivity.start(it)}
            }

            root?.findViewById<Button>(R.id.updateCustomer)?.setOnClickListener{
                (customerDetailFragment.activity as CustomerDetailsActivity).onUserWantToUpdateCustomer()
            }

            root?.findViewById<Button>(R.id.deleteCustomer)?.setOnClickListener{
                val builder = customerDetailFragment.context?.let { it1 -> AlertDialog.Builder(it1) }

                builder?.setTitle(context.getString(R.string.DeleteCustomer))
                builder?.setMessage(context.getString(R.string.EnterAdminPass))
                val input = EditText(customerDetailFragment.context)
                val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

                input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                input.layoutParams = lp
                builder?.setView(input)

                builder?.setPositiveButton(context.getString(R.string.yes)){_, _ ->
                    if (input.text.toString() == Account.register.password.toString()){
                        onUserWantToRemoveCustomer()
                    } else {
                        Toast.makeText(customerDetailFragment.context,context.getString(R.string.MauvaisMdp),Toast.LENGTH_SHORT).show()
                    }
                }
                builder?.setNegativeButton(context.getString(R.string.no)){_,_ -> }
                builder?.setNeutralButton(context.getString(R.string.Cancel)){ _, _ -> }

                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }

            onUserWantToShowDetailCustomer()
        } catch (exception : Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToShowDetailCustomer(){
        customerDetailFragmentViewModel.showDetailOfCustomer(root!!)
    }

    override fun onUserWantToRemoveCustomer() {
        Fuel.post(requestHttp.url+"Customer/Remove/" + CustomerSelected.customer.ID)
            .body("picture=" +CustomerSelected.pool.picture)
            .header("Content-Type" to "application/x-www-form-urlencoded")
            .responseString { _, _, result ->
                result.fold({
                    Toast.makeText(customerDetailFragment.context,"Deleted !",Toast.LENGTH_SHORT).show()
                    (customerDetailFragment.activity as CustomerDetailsActivity).onBackPressed()
                }, { err ->
                    println(err.message)
                })
            }
    }
}
