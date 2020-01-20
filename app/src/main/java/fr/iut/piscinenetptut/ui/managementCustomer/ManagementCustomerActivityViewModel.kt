package fr.iut.piscinenetptut.ui.managementCustomer

import android.view.View
import  android.widget.*
import androidx.lifecycle.MutableLiveData
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.createUniqueIdV4
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.customView.RecursiveRadioGroup

class ManagementCustomerActivityViewModel {

    val TAG: String = "AddCustomerActivityViewModel"

    val addCustomerCallBack: MutableLiveData<Customer> = MutableLiveData()
    val addPoolCallBack: MutableLiveData<Pool> = MutableLiveData()

    val updateCustomerCallBack: MutableLiveData<Customer> = MutableLiveData()
    val updatePoolCallBack: MutableLiveData<Pool> = MutableLiveData()

    fun onNeedToGetCustomerInformation(root: View, id: Int? = null) {
        try {
            val idTypeOfContract = root.findViewById<RadioGroup>(R.id.addCustomerRadioTypeContract)?.checkedRadioButtonId
            val idContractOfProduct = root.findViewById<RadioGroup>(R.id.addCustomerRadioContractOfProduct)?.checkedRadioButtonId
            val customer = Customer (
                ID = id,
                telFixNumber = getValue(root.findViewById<EditText>(R.id.addCustomerTelFixNumber)?.text.toString()),
                guardianNumber = getValue(root.findViewById<EditText>(R.id.addCustomerInputGuardian)?.text.toString()),
                name = root.findViewById<EditText>(R.id.addCustomerName)?.text.toString(),
                surname = root.findViewById<EditText>(R.id.addCustomerSurname)?.text.toString(),
                mail = root.findViewById<EditText>(R.id.addCustomerMail)?.text.toString(),
                address = root.findViewById<EditText>(R.id.addCustomerAddr)?.text.toString(),
                town = root.findViewById<EditText>(R.id.addCustomerTown)?.text.toString(),
                postalCode = root.findViewById<EditText>(R.id.addCustomerPostalCode)?.text.toString().toInt(),
                telPhoneNumber = root.findViewById<EditText>(R.id.addCustomerTelPhoneNumber)?.text.toString(),
                typeOfContract = root.findViewById<RadioButton>(idTypeOfContract!!)?.text.toString(),
                contractOfProduct = root.findViewById<RadioButton>(idContractOfProduct!!)?.text.toString()
            )

            if (id != null){
                updateCustomerCallBack.postValue(customer)
            } else {
                addCustomerCallBack.postValue(customer)
            }
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    fun onNeedToGetPoolInformation(root: View,customer: Customer?, ID_Customer: Int?, picture: String?= "picture" + createUniqueIdV4() + ".jpg") {
        try {
            val idShape = root.findViewById<RecursiveRadioGroup>(R.id.addPoolShape)?.checkedItemId
            val idEnvironment = root.findViewById<RecursiveRadioGroup>(R.id.addPoolEnvironment)?.checkedItemId
            val idState = root.findViewById<RecursiveRadioGroup>(R.id.addPoolState)?.checkedItemId
            val idCover = root.findViewById<RecursiveRadioGroup>(R.id.addPoolTypeOfCover)?.checkedItemId
            val idAcces = root.findViewById<RadioGroup>(R.id.addPoolAcces)?.checkedRadioButtonId
            val idElectronicalProduct = root.findViewById<RadioGroup>(R.id.addPoolElectronicalProduct)?.checkedRadioButtonId
            val pool = Pool (
                 ID_Customer = ID_Customer,
                 picture = picture,
                 sizeLo = root.findViewById<EditText>(R.id.addPoolLo)?.text.toString(),
                 sizeLa = root.findViewById<EditText>(R.id.addPoolLa)?.text.toString(),
                 depth = root.findViewById<EditText>(R.id.addPoolDepth)?.text.toString(),
                 shape = root.findViewById<RadioButton>(idShape!!)?.text.toString(),
                 environment = root.findViewById<RadioButton>(idEnvironment!!)?.text.toString(),
                 state = root.findViewById<RadioButton>(idState!!)?.text.toString(),
                 cover = root.findViewById<RadioButton>(idCover!!)?.text.toString(),
                 distance = root.findViewById<EditText>(R.id.addPoolDistance)?.text.toString(),
                 warning = root.findViewById<TextView>(R.id.addPoolTextValueOfSwitchWarning)?.text.toString(), // pas bn ---------------------------------
                 acces = root.findViewById<RadioButton>(idAcces!!)?.text.toString(),
                 winterCover = root.findViewById<TextView>(R.id.addPoolTextValueOfSwitchWinterCover)?.text.toString(), // pas bn ---------------------------------
                 dateSandFilter = getValue(root.findViewById<EditText>(R.id.dateSandFilter)?.text.toString()),
                 brandSandFilter = getValue(root.findViewById<EditText>(R.id.brandSandFilter)?.text.toString()),
                 brandFilter = getValue(root.findViewById<EditText>(R.id.addPoolBrandFilter)?.text.toString()),
                 cvFilter = getValue(root.findViewById<EditText>(R.id.cvFilter)?.text.toString()),
                 dateFilter = getValue(root.findViewById<EditText>(R.id.dateFilter)?.text.toString()),
                 electronicalProduct = root.findViewById<RadioButton>(idElectronicalProduct!!)?.text.toString(),
                 dateElectronicalProduct = root.findViewById<EditText>(R.id.addPoolDateElectronicalProduct)?.text.toString(),
                 datePH = getValue(root.findViewById<EditText>(R.id.addPoolDatePH)?.text.toString()),
                 datePomp = getValue(root.findViewById<EditText>(R.id.addPoolDatePompe)?.text.toString()),
                 dateRemp = getValue(root.findViewById<EditText>(R.id.addPoolDateRemp)?.text.toString()),
                 observation = getValue(root.findViewById<EditText>(R.id.addPoolObservation)?.text.toString())
            )
            if (customer != null){
                updatePoolCallBack.postValue(pool)
            } else {
                addPoolCallBack.postValue(pool)
            }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    fun getValue(str: String): String? {
        return if(str == "") null else str
    }
}