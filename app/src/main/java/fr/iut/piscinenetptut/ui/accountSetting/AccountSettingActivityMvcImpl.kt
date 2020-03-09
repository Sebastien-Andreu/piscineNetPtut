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

            accountSettingActivity.activity?.findViewById<TextView>(R.id.textToolBar)?.text = context.getString(R.string.AccountSetting)


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

    override fun verifyIfAllInputPassIsNotEmpty(): Boolean {
        return ( !root?.findViewById<EditText>(R.id.accountSettingOldPass)?.text.isNullOrEmpty() &&
                 !root?.findViewById<EditText>(R.id.accountSettingNewPassVerify)?.text.isNullOrEmpty() &&
                 !root?.findViewById<EditText>(R.id.accountSettingNewPass)?.text.isNullOrEmpty())
    }

    override fun verifyIfAllInputMailIsNotEmpty(): Boolean {
        return (!root?.findViewById<EditText>(R.id.accountSettingNewAddr)?.text.isNullOrEmpty() &&
                !root?.findViewById<EditText>(R.id.accountSettingNewAddrVerify)?.text.isNullOrEmpty())
    }

    override fun verifyDataOfPassword(): Boolean {
        return if (verifyIfAllInputPassIsNotEmpty()){
            if (root?.findViewById<EditText>(R.id.accountSettingOldPass)?.text.toString() == Account.register.password){
                return if (root?.findViewById<EditText>(R.id.accountSettingNewPass)?.text.toString() == root?.findViewById<EditText>(R.id.accountSettingNewPassVerify)?.text.toString()){
                    true
                } else {
                    Toast.makeText(context, context.getString(R.string.mdpCorrespondPas), Toast.LENGTH_LONG).show()
                    false
                }
            } else {
                Toast.makeText(context, context.getString(R.string.mdpPasBon), Toast.LENGTH_LONG).show()
                false
            }
        } else {
            Toast.makeText(context, context.getString(R.string.ErrorMdp), Toast.LENGTH_LONG).show()
            false
        }
    }

    override fun verifyDataOfMail(): Boolean {
        return if (verifyIfAllInputMailIsNotEmpty()){
            if (root?.findViewById<EditText>(R.id.accountSettingNewAddr)?.text.toString() == root?.findViewById<EditText>(R.id.accountSettingNewAddrVerify)?.text.toString()){
                return true
            } else {
                Toast.makeText(context, context.getString(R.string.addrMailCorrespondPas), Toast.LENGTH_LONG).show()
                false
            }
        } else {
            Toast.makeText(context, context.getString(R.string.ErrorMdp), Toast.LENGTH_LONG).show()
            false
        }
    }

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