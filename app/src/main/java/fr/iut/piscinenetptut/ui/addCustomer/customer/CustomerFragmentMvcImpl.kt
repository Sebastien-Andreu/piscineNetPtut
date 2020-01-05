package fr.iut.piscinenetptut.ui.addCustomer.customer

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor

class CustomerFragmentMvcImpl (
    private val context: Context,
    private val customerFragment: CustomerFragment
    ): CustomerFragmentMvc {

    private val TAG = "CustomerFragmentMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.fragment_add_customer, null)

            root?.findViewById<Switch>(R.id.addCustomerSwitchGuardian)?.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    root!!.findViewById<LinearLayout>(R.id.layoutGuardian).visibility = View.VISIBLE
                    root!!.findViewById<TextView>(R.id.addCustomerInputGuardian)?.text = null
                    return@setOnCheckedChangeListener
                }
                root!!.findViewById<LinearLayout>(R.id.layoutGuardian).visibility = View.GONE
            }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun verifyAllInput(): Boolean {
        return (verifyIfAllInputTextAreNotEmpty() && verifyIfAllRadioGroupAreNotEmpty() && verifyIfGuardianIsSelectedAndNotEmpty())
    }

    private fun verifyIfAllInputTextAreNotEmpty(): Boolean{
        return ( !root?.findViewById<TextView>(R.id.addCustomerSurname)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addCustomerName)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addCustomerMail)?.text.isNullOrEmpty() &&
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