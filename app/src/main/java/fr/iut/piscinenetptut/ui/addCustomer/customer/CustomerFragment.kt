package fr.iut.piscinenetptut.ui.addCustomer.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import fr.iut.piscinenetptut.R

class CustomerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View? = inflater.inflate(R.layout.fragment_add_customer, container, false)

        root?.findViewById<Switch>(R.id.addCustomerSwitchGuardian)?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                root.findViewById<LinearLayout>(R.id.layoutGuardian).visibility = View.VISIBLE
                root.findViewById<TextView>(R.id.addCustomerInputGuardian)?.text = null
                return@setOnCheckedChangeListener
            }
            root.findViewById<LinearLayout>(R.id.layoutGuardian).visibility = View.GONE
        }
        return root
    }

    fun verifyIfAllInputAreNotEmpty(): Boolean{
        return (verifyIfAllInputTextAreNotEmpty() && verifyIfAllRadioGroupAreNotEmpty() && verifyIfGuardianIsSelectedAndNotEmpty())
    }

    private fun verifyIfAllInputTextAreNotEmpty(): Boolean{
        return ( !this@CustomerFragment.activity?.findViewById<TextView>(R.id.addCustomerSurname)?.text.isNullOrEmpty() &&
                 !this@CustomerFragment.activity?.findViewById<TextView>(R.id.addCustomerName)?.text.isNullOrEmpty() &&
                 !this@CustomerFragment.activity?.findViewById<TextView>(R.id.addCustomerMail)?.text.isNullOrEmpty() &&
                 !this@CustomerFragment.activity?.findViewById<TextView>(R.id.addCustomerTown)?.text.isNullOrEmpty() &&
                 !this@CustomerFragment.activity?.findViewById<TextView>(R.id.addCustomerPostalCode)?.text.isNullOrEmpty() &&
                 !this@CustomerFragment.activity?.findViewById<TextView>(R.id.addCustomerTelPhoneNumber)?.text.isNullOrEmpty())
    }

    private fun verifyIfAllRadioGroupAreNotEmpty(): Boolean{
        return ( this@CustomerFragment.activity?.findViewById<RadioGroup>(R.id.addCustomerRadioTypeContract)?.checkedRadioButtonId != -1 &&
                 this@CustomerFragment.activity?.findViewById<RadioGroup>(R.id.addCustomerRadioContractOfProduct)?.checkedRadioButtonId != -1)
    }

    private fun verifyIfGuardianIsSelectedAndNotEmpty(): Boolean{
        if (View.VISIBLE == this@CustomerFragment.activity?.findViewById<LinearLayout>(R.id.layoutGuardian)?.visibility) {
            return !this@CustomerFragment.activity?.findViewById<TextView>(R.id.addCustomerInputGuardian)?.text.isNullOrEmpty()
        }
        return true
    }
}
