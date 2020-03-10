package fr.iut.piscinenetptut.ui.forgottenPassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.mail.SendMailNewPassword
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.UnionKind
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import org.json.JSONObject
import kotlin.math.floor

class ForgottenPasswordActivity : AppCompatActivity(), ForgottenPasswordMvc.Listeners {
    companion object {
        private val TAG:String = "ForgottenPasswordActivity"

        fun start(context: Context) {
            try {
                context.startActivity(Intent(context, ForgottenPasswordActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    private lateinit var forgottenPasswordMvcImpl: ForgottenPasswordMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = getString(R.string.forgottenPassword)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            forgottenPasswordMvcImpl = ForgottenPasswordMvcImpl(this, this)
            setContentView(forgottenPasswordMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        this@ForgottenPasswordActivity.finish()
    }

    override fun onUserWantToSendNewPassword() {
        var newPassword = getPassword();
        var mailAdress = forgottenPasswordMvcImpl.root!!.findViewById<EditText>(R.id.forgottenPasswordMail).text.toString();
        val requestHttp = httpRequest()
        Fuel.get(requestHttp.url+"MailExists/"+mailAdress)
            .header("Content-Type" to "application/x-www-form-urlencoded")
            .responseString { _, _, result ->
                result.fold({d ->
                    val json = Json(JsonConfiguration.Stable)
                    val user = JSONObject(d);
                    val id = user.getInt("ID");
                    updatePassword(id, newPassword)
                    var mail = SendMailNewPassword()
                    mail.send(this, newPassword, mailAdress)
                }, { err ->
                    Toast.makeText(forgottenPasswordMvcImpl.root?.context, err.message, Toast.LENGTH_LONG).show()
                })
            }
        Log.d("fp",requestHttp.url+"MailExists/"+forgottenPasswordMvcImpl.root!!.findViewById<EditText>(R.id.forgottenPasswordMail).text.toString())
    }

    fun updatePassword(id : Int, password : String){
        val requestHttp = httpRequest()
        try {
            Fuel.put(requestHttp.url+"User/" + id + "/Password")
                .body("password="+password)
                .header("Content-Type" to "application/x-www-form-urlencoded")
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    fun getPassword() : String{
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var passWord = ""
        for (i in 0..8) {
            passWord += chars[floor(Math.random() * chars.length).toInt()]
        }
        return passWord;
    }
}