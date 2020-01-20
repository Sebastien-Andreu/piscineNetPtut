package fr.iut.piscinenetptut.ui.accountSetting

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor

class AccountSettingActivityMvcImpl (
    val accountSettingActivity: AccountSettingActivity,
    val context: Context
): AccountSettingActivityMvc {

    val TAG: String = "HomeActivtyMvcImpl"
    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.layout_account_management, null)

            if (null != root) {
                root?.findViewById<Button>(R.id.accountSettingConfirmPass)?.setOnClickListener {
                    if (verifyDataOfPassword()){
                        Toast.makeText(context, "ok pass", Toast.LENGTH_LONG).show()
                    }
                }
                root?.findViewById<Button>(R.id.accountSettingConfirmAddr)?.setOnClickListener {
                    if (verifyDataOfMail()){
                        Toast.makeText(context, "ok mail", Toast.LENGTH_LONG).show()
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
            if (root?.findViewById<EditText>(R.id.accountSettingOldPass)?.text.toString() == accountSettingActivity.register.password){
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

    override fun updatePassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateMail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}