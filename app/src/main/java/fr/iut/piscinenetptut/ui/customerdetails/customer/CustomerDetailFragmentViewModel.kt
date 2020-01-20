package fr.iut.piscinenetptut.ui.customerdetails.customer

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import java.lang.Exception

class CustomerDetailFragmentViewModel {

    private val TAG: String = "CustomerDetailFragmentViewModel"



    fun showDetailOfCustomer(root: View){
        try {
            val customer = CustomerSelected.customer

            root.findViewById<TextView>(R.id.detailCustomerName).text = customer.name
            root.findViewById<TextView>(R.id.detailCustomerSurname).text = customer.surname
            root.findViewById<TextView>(R.id.detailCustomerMail).text = customer.mail
            root.findViewById<TextView>(R.id.detailCustomerAddr).text = customer.address
            root.findViewById<TextView>(R.id.detailCustomerPostalCode).text = customer.postalCode.toString()
            root.findViewById<TextView>(R.id.detailCustomerPhoneNumber).text = customer.telPhoneNumber
            if (customer.telFixNumber != null.toString()){
                root.findViewById<TextView>(R.id.detailCustomerFixPhoneNumber).visibility = View.VISIBLE
                root.findViewById<TextView>(R.id.detailCustomerFixPhoneNumber).text = customer.telFixNumber
            }
            if (customer.guardianNumber != null.toString()){
                root.findViewById<LinearLayout>(R.id.detailCustomerLayoutGuardian).visibility = View.VISIBLE
                root.findViewById<TextView>(R.id.detailCustomerGuardianNumber).text = customer.guardianNumber
            }

            root.findViewById<TextView>(R.id.detailCustomerTypeOfVisit).text = customer.typeOfContract
            root.findViewById<TextView>(R.id.detailCustomerContractOfProduct).text = customer.contractOfProduct

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}