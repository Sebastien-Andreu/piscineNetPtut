package fr.iut.piscinenetptut.ui.managementCustomer.customer

import android.content.Context
import android.view.View
import android.widget.*
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.managementCustomer.ManagementCustomerActivity

class CustomerFragmentMvcImpl (
    private val context: Context,
    private val customerFragment: CustomerFragment
    ): CustomerFragmentMvc {

    private val TAG = "CustomerFragmentMvcImpl"

    lateinit var customerFragmentViewModel: CustomerFragmentViewModel


    var root: View? = null

    init {
        try {
            customerFragmentViewModel = CustomerFragmentViewModel()

            root = View.inflate(context, R.layout.fragment_add_customer, null)

            root?.findViewById<Switch>(R.id.addCustomerSwitchGuardian)?.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    root!!.findViewById<LinearLayout>(R.id.layoutGuardian)?.visibility = View.VISIBLE
                    root!!.findViewById<TextView>(R.id.addCustomerInputGuardian)?.text = null
                    return@setOnCheckedChangeListener
                }
                root!!.findViewById<TextView>(R.id.addCustomerInputGuardian)?.text = null
                root!!.findViewById<LinearLayout>(R.id.layoutGuardian)?.visibility = View.GONE

            }


            if ((customerFragment.activity as ManagementCustomerActivity).customer != null) {
                root!!.findViewById<LinearLayout>(R.id.updateCustomerLayout)?.visibility = View.VISIBLE
                root!!.findViewById<Button>(R.id.updateCustomerButton)?.setOnClickListener{
                    (customerFragment.activity as ManagementCustomerActivity).onUserWantToAddNewCustomer()
                }
                root!!.findViewById<Button>(R.id.updateCustomerButtonCancel)?.setOnClickListener{
                    (customerFragment.activity as ManagementCustomerActivity).onBackPressed()
                }
                onUserWantToShowDetailCustomerToUpdate()
            }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToShowDetailCustomerToUpdate() {
        customerFragmentViewModel.showInformationOfCustomerWhenUserWantToUpdate(root!!, (customerFragment.activity as ManagementCustomerActivity).customer!!)
    }



    override fun verifyAllInput(): Boolean {
        return (verifyIfAllInputTextAreNotEmpty() && verifyIfAllRadioGroupAreNotEmpty() && verifyIfGuardianIsSelectedAndNotEmpty())
    }

    private fun verifyIfAllInputTextAreNotEmpty(): Boolean{
        return ( !root?.findViewById<TextView>(R.id.addCustomerSurname)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addCustomerName)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addCustomerMail)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addCustomerAddr)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addCustomerTown)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addCustomerPostalCode)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addCustomerTelPhoneNumber)?.text.isNullOrEmpty())
    }

    private fun verifyIfAllRadioGroupAreNotEmpty(): Boolean{
        return ( root?.findViewById<RadioGroup>(R.id.addCustomerRadioTypeContract)?.checkedRadioButtonId != -1 &&
                root?.findViewById<RadioGroup>(R.id.addCustomerRadioContractOfProduct)?.checkedRadioButtonId != -1)
    }

    private fun verifyIfGuardianIsSelectedAndNotEmpty(): Boolean{
        if (View.VISIBLE == root?.findViewById<LinearLayout>(R.id.layoutGuardian)?.visibility) {
            return !root?.findViewById<TextView>(R.id.addCustomerInputGuardian)?.text.isNullOrEmpty()
        }
        return true
    }
}