package fr.iut.piscinenetptut.ui.accountSetting

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Account
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class AccountSettingActivityMvcImpl (
    val accountSettingActivity: AccountSettingActivity,
    val context: Context
): AccountSettingActivityMvc {

    val TAG: String = "HomeActivtyMvcImpl"

    private val requestHttp = httpRequest()

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.layout_account_management, null)

            accountSettingActivity.activity?.findViewById<TextView>(R.id.textToolBar)?.text = "Account Settings"


            if (null != root) {
                root?.findViewById<Button>(R.id.accountSettingConfirmPass)?.setOnClickListener {
                    if (verifyDataOfPassword()){
                        updatePassword()
                    }
                }
                root?.findViewById<Button>(R.id.accountSettingConfirmAddr)?.setOnClickListener {
                    if (verifyDataOfMail()){
                        updateMail()
                    }
                }
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    /**
    *   Check old password field, new password field and password verify field not to be empty
    *   @return true if one or more fields is empty
    **/
    override fun verifyIfAllInputPassIsNotEmpty(): Boolean {
        return ( !root?.findViewById<EditText>(R.id.accountSettingOldPass)?.text.isNullOrEmpty() &&
                 !root?.findViewById<EditText>(R.id.accountSettingNewPassVerify)?.text.isNullOrEmpty() &&
                 !root?.findViewById<EditText>(R.id.accountSettingNewPass)?.text.isNullOrEmpty())
    }

    /**
    *   Check mail field and mail verify field not to be empty
    *   @return true if one or more fields is empty, else return false
    **/
    override fun verifyIfAllInputMailIsNotEmpty(): Boolean {
        return (!root?.findViewById<EditText>(R.id.accountSettingNewAddr)?.text.isNullOrEmpty() &&
                !root?.findViewById<EditText>(R.id.accountSettingNewAddrVerify)?.text.isNullOrEmpty())
    }

    /**
    *   Check password field and password verify field to be identical
    *   @return true if they are identical, else return false
    **/
    override fun verifyDataOfPassword(): Boolean {
        return if (verifyIfAllInputPassIsNotEmpty()){
            if (root?.findViewById<EditText>(R.id.accountSettingOldPass)?.text.toString() == Account.register.password){
                return if (root?.findViewById<EditText>(R.id.accountSettingNewPass)?.text.toString() == root?.findViewById<EditText>(R.id.accountSettingNewPassVerify)?.text.toString()){
                    true
                } else {
                    Toast.makeText(context, "Les mots de passe ne correspondent pas", Toast.LENGTH_LONG).show()
                    false
                }
            } else {
                Toast.makeText(context, "Le mot de passe n'est pas bon", Toast.LENGTH_LONG).show()
                false
            }
        } else {
            Toast.makeText(context, "pas bon", Toast.LENGTH_LONG).show()
            false
        }
    }

    /**
    *   Check mail field and mail verify field to be identical
    *   @return true if they are identical, else return false
    **/
    override fun verifyDataOfMail(): Boolean {
        return if (verifyIfAllInputMailIsNotEmpty()){
            if (root?.findViewById<EditText>(R.id.accountSettingNewAddr)?.text.toString() == root?.findViewById<EditText>(R.id.accountSettingNewAddrVerify)?.text.toString()){
                return true
            } else {
                Toast.makeText(context, "Les addresses mail ne correspondent pas", Toast.LENGTH_LONG).show()
                false
            }
        } else {
            Toast.makeText(context, "pas bon", Toast.LENGTH_LONG).show()
            false
        }
    }

    /**
    *   Change user password from the fields
    **/
    override fun updatePassword() {
        try {
            Fuel.put(requestHttp.url+"User/" + Account.register.ID + "/Password")
                .body("password=" + root?.findViewById<EditText>(R.id.accountSettingNewPass)?.text.toString())
                .header("Content-Type" to "application/x-www-form-urlencoded")
                .responseString { _, _, result ->
                    result.fold({
                        Toast.makeText(accountSettingActivity.context, "Password Updated", Toast.LENGTH_LONG).show()
                        root?.findViewById<EditText>(R.id.accountSettingOldPass)?.setText("")
                        root?.findViewById<EditText>(R.id.accountSettingNewPassVerify)?.setText("")
                        root?.findViewById<EditText>(R.id.accountSettingNewPass)?.setText("")
                    }, { err ->
                        Toast.makeText(accountSettingActivity.context, "Error", Toast.LENGTH_LONG).show()
                        println(err.message)
                    })
                }
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }

    }

    /**
    *   Change user mail from the fields
    **/
    override fun updateMail() {
        try {
            Fuel.put(requestHttp.url+"User/" + Account.register.ID + "/Mail")
                .body("mail=" + root?.findViewById<EditText>(R.id.accountSettingNewAddr)?.text.toString())
                .header("Content-Type" to "application/x-www-form-urlencoded")
                .responseString { _, _, result ->
                    result.fold({
                        Toast.makeText(accountSettingActivity.context, "Mail Updated", Toast.LENGTH_LONG).show()
                        root?.findViewById<EditText>(R.id.accountSettingNewAddr)?.setText("")
                        root?.findViewById<EditText>(R.id.accountSettingNewAddrVerify)?.setText("")
                    }, { err ->
                        Toast.makeText(accountSettingActivity.context, "Error", Toast.LENGTH_LONG).show()
                        println(err.message)
                    })
                }
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}