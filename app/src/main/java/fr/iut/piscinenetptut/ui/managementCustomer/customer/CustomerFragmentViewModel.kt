package fr.iut.piscinenetptut.ui.managementCustomer.customer

import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.core.view.forEach
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor

class CustomerFragmentViewModel {

    private var TAG: String = "CustomerFragmentViewModel"


    fun showInformationOfCustomerWhenUserWantToUpdate(root: View) {
        try {
            val customer = CustomerSelected.customer

            root.findViewById<EditText>(R.id.addCustomerName)?.setText(customer.name)
            root.findViewById<EditText>(R.id.addCustomerSurname)?.setText(customer.surname)
            root.findViewById<EditText>(R.id.addCustomerMail)?.setText(customer.mail)
            root.findViewById<EditText>(R.id.addCustomerAddr)?.setText(customer.address)
            root.findViewById<EditText>(R.id.addCustomerTown)?.setText(customer.town)
            root.findViewById<EditText>(R.id.addCustomerPostalCode)?.setText(customer.postalCode.toString())
            root.findViewById<EditText>(R.id.addCustomerTelPhoneNumber)?.setText(customer.telPhoneNumber.toString())

            if (customer.telFixNumber != null.toString()){
                root.findViewById<EditText>(R.id.addCustomerTelFixNumber)?.setText(customer.telFixNumber.toString())
            }

            if (customer.guardianNumber != null.toString()){
                root.findViewById<Switch>(R.id.addCustomerSwitchGuardian)?.isChecked = true
                root.findViewById<EditText>(R.id.addCustomerInputGuardian)?.setText(customer.guardianNumber) // show number
            }

            root.findViewById<RadioGroup>(R.id.addCustomerRadioTypeContract)?.forEach {
                if ((it as RadioButton).text == customer.typeOfContract){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }

            root.findViewById<RadioGroup>(R.id.addCustomerRadioContractOfProduct)?.forEach {
                if ((it as RadioButton).text == customer.contractOfProduct){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}