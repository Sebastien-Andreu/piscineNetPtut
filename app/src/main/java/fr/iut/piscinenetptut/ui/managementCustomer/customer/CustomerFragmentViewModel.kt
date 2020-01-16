package fr.iut.piscinenetptut.ui.managementCustomer.customer

import android.view.View
import android.widget.EditText
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.library.extension.toTreatFor

class CustomerFragmentViewModel {

    private var TAG: String = "CustomerFragmentViewModel"

    fun showInformationOfCustomerWhenUserWantToUpdate(root: View, customer: Customer) {
        try {
            println("------------------------------------------------3")

//            val idTypeOfContract = root.findViewById<RadioGroup>(R.id.addCustomerRadioTypeContract)?.checkedRadioButtonId
//            val idContractOfProduct = root.findViewById<RadioGroup>(R.id.addCustomerRadioContractOfProduct)?.checkedRadioButtonId
//            val customer = Customer (
                root.findViewById<EditText>(R.id.addCustomerTelFixNumber)?.setText(customer.telFixNumber.toString())
                root.findViewById<EditText>(R.id.addCustomerInputGuardian)?.setText(customer.guardianNumber) // show number
                root.findViewById<EditText>(R.id.addCustomerName)?.setText(customer.name)
                root.findViewById<EditText>(R.id.addCustomerSurname)?.setText(customer.surname)
                root.findViewById<EditText>(R.id.addCustomerMail)?.setText(customer.mail)
                root.findViewById<EditText>(R.id.addCustomerAddr)?.setText(customer.address)
                root.findViewById<EditText>(R.id.addCustomerTown)?.setText(customer.town)
                root.findViewById<EditText>(R.id.addCustomerPostalCode)?.setText(customer.postalCode.toString())
                root.findViewById<EditText>(R.id.addCustomerTelPhoneNumber)?.setText(customer.telPhoneNumber.toString())
//                root.findViewById<RadioButton>(idTypeOfContract!!)?.text.toString(),
//                root.findViewById<RadioButton>(idContractOfProduct!!)?.text.toString()
//            )
//            customerCallBack.postValue(customer)
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}