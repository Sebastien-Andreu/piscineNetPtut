package fr.iut.piscinenetptut.ui.splashscreen

import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Account
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.toTreatFor

class SplashScreenActivityViewModel {

    val Tag: String = "SplashScreenActivityViewModel"

    val registerCallBack: MutableLiveData<Account> = MutableLiveData()

    fun onNeedToGetCustomerInformation(root: View) {
        try {
            val register = Register (
                login = root.findViewById<EditText>(R.id.registerLogin).text.toString(),
                password = root.findViewById<EditText>(R.id.registerPassword).text.toString()
            )
            Account.register = register
            registerCallBack.postValue(Account)
        } catch (exception: Exception){
            exception.toTreatFor(Tag)
        }
    }
}