package fr.iut.piscinenetptut.ui.customerdetails.customer

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.lang.Exception

class CustomerDetailFragmentViewModel {

    private val TAG: String = "CustomerDetailFragmentViewModel"
    private val json = Json(JsonConfiguration.Stable)
    private val requestHttp = httpRequest()

    /**
    *   Show customer details in the view 
    *   @param View The view to show the details in
    **/
    fun showDetailOfCustomer(root: View){
        try {

            Fuel.get(requestHttp.url+"Customer/" + CustomerSelected.customer.ID)
                .responseString { _, _, result ->
                    result.fold({ d ->

                        CustomerSelected.customer = json.parse(Customer.serializer(), d)
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
                    }, { err ->
                        println(err.message)
                    })
                }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}