package fr.iut.piscinenetptut.ui.addCustomer

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.library.extension.toTreatFor

class AddCustomerActivityViewModel {

    val Tag: String = "AddCustomerActivityViewModel"

    val customerCallBack: MutableLiveData<Customer> = MutableLiveData()

    fun onNeedToGetCustomerInformation(root: View?) {
        try {
            if (null != root){
                val idTypeOfContract = root.findViewById<RadioGroup>(R.id.addCustomerRadioTypeContract)?.checkedRadioButtonId
                val idContractOfProduct = root.findViewById<RadioGroup>(R.id.addCustomerRadioContractOfProduct)?.checkedRadioButtonId
                val customer = Customer (
                    ID = null,
                    telFixNumber = root.findViewById<TextView>(R.id.addCustomerTelFixNumber)?.text,
                    guardianNumber = root.findViewById<TextView>(R.id.addCustomerInputGuardian)?.text,
                    name = root.findViewById<TextView>(R.id.addCustomerName)?.text,
                    surname = root.findViewById<TextView>(R.id.addCustomerSurname)?.text,
                    mail = root.findViewById<TextView>(R.id.addCustomerMail)?.text,
                    town = root.findViewById<TextView>(R.id.addCustomerTown)?.text,
                    postalCode = root.findViewById<TextView>(R.id.addCustomerPostalCode)?.text,
                    telPhoneNumber = root.findViewById<TextView>(R.id.addCustomerTelPhoneNumber)?.text,
                    typeOfContract = root.findViewById<RadioButton>(idTypeOfContract!!)?.text,
                    contractOfProduct = root.findViewById<RadioButton>(idContractOfProduct!!)?.text
                )
                customerCallBack.postValue(customer)
            }
        } catch (exception: Exception){
            exception.toTreatFor(Tag)
        }
    }

}