package fr.iut.piscinenetptut.ui.addCustomer

import android.view.View
import android.widget.*
import androidx.lifecycle.MutableLiveData
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.createUniqueIdV4
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.customView.RecursiveRadioGroup

class AddCustomerActivityViewModel {

    val Tag: String = "AddCustomerActivityViewModel"

    val customerCallBack: MutableLiveData<Customer> = MutableLiveData()
    val poolCallBack: MutableLiveData<Pool> = MutableLiveData()


    fun onNeedToGetCustomerInformation(root: View?) {
        try {
            if (null != root){
                val idTypeOfContract = root.findViewById<RadioGroup>(R.id.addCustomerRadioTypeContract)?.checkedRadioButtonId
                val idContractOfProduct = root.findViewById<RadioGroup>(R.id.addCustomerRadioContractOfProduct)?.checkedRadioButtonId
                val customer = Customer (
                    ID = null,
                    telFixNumber = root.findViewById<EditText>(R.id.addCustomerTelFixNumber)?.text,
                    guardianNumber = root.findViewById<EditText>(R.id.addCustomerInputGuardian)?.text,
                    name = root.findViewById<EditText>(R.id.addCustomerName)?.text,
                    surname = root.findViewById<EditText>(R.id.addCustomerSurname)?.text,
                    mail = root.findViewById<EditText>(R.id.addCustomerMail)?.text,
                    town = root.findViewById<EditText>(R.id.addCustomerTown)?.text,
                    postalCode = root.findViewById<EditText>(R.id.addCustomerPostalCode)?.text,
                    telPhoneNumber = root.findViewById<EditText>(R.id.addCustomerTelPhoneNumber)?.text,
                    typeOfContract = root.findViewById<RadioButton>(idTypeOfContract!!)?.text,
                    contractOfProduct = root.findViewById<RadioButton>(idContractOfProduct!!)?.text
                )
                customerCallBack.postValue(customer)
            }
        } catch (exception: Exception){
            exception.toTreatFor(Tag)
        }
    }

    fun onNeedToGetPoolInformation(root: View?, idCustomer: Int?) {
        try {
            if (null != root){
                val idEnvironement = root.findViewById<RecursiveRadioGroup>(R.id.addPoolEnvironment)?.checkedItemId
                val idState = root.findViewById<RecursiveRadioGroup>(R.id.addPoolState)?.checkedItemId
                val idCover = root.findViewById<RecursiveRadioGroup>(R.id.addPoolTypeOfCover)?.checkedItemId
                val idAcces = root.findViewById<RadioGroup>(R.id.addPoolAcces)?.checkedRadioButtonId
                val idElectronicalProduct = root.findViewById<RadioGroup>(R.id.addPoolElectronicalProduct)?.checkedRadioButtonId
                val pool = Pool (
                     ID = null,
                     ID_Customer = idCustomer,
                     picture = "picture" + createUniqueIdV4(),
                     sizeLo = root.findViewById<EditText>(R.id.addPoolLo)?.text,
                     sizeLa = root.findViewById<EditText>(R.id.addPoolLa)?.text,
                     depth = root.findViewById<EditText>(R.id.addPoolDepth)?.text,
                     environment = root.findViewById<RadioButton>(idEnvironement!!)?.text,
                     state = root.findViewById<RadioButton>(idState!!)?.text,
                     cover = root.findViewById<RadioButton>(idCover!!)?.text,
                     distance = root.findViewById<EditText>(R.id.addPoolDistance)?.text,
                     warning = root.findViewById<Switch>(R.id.addPoolWarning)?.text, // pas bn ---------------------------------
                     acces = root.findViewById<RadioButton>(idAcces!!)?.text,
                     winterCover = root.findViewById<Switch>(R.id.addPoolWinterCover)?.text, // pas bn ---------------------------------
                     dateSandFilter = root.findViewById<EditText>(R.id.dateSandFilter)?.text,
                     brandSandFilter = root.findViewById<EditText>(R.id.brandSandFilter)?.text,
                     brandFilter = root.findViewById<EditText>(R.id.brandFilter)?.text,
                     cvFilter = root.findViewById<EditText>(R.id.cvFilter)?.text,
                     dateFilter = root.findViewById<EditText>(R.id.dateFilter)?.text,
                     electronicalProduct = root.findViewById<RadioButton>(idElectronicalProduct!!)?.text,
                     dateElectronicalPoduct = root.findViewById<EditText>(R.id.addPoolDateElectronicalProduct)?.text,
                     datePH = root.findViewById<EditText>(R.id.addPoolDatePH)?.text,
                     datePomp = root.findViewById<EditText>(R.id.addPoolDatePompe)?.text,
                     dateRemp = root.findViewById<EditText>(R.id.addPoolDateRemp)?.text,
                     observation = root.findViewById<EditText>(R.id.addPoolObservation)?.text
                )
                poolCallBack.postValue(pool)
            }
        } catch (exception: Exception){
            exception.toTreatFor(Tag)
        }
    }
}