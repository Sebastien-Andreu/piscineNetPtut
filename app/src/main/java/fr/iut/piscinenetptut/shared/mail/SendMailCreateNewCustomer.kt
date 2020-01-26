package fr.iut.piscinenetptut.shared.mail

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.ui.managementCustomer.ManagementCustomerActivity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.math.floor

class SendMailCreateNewCustomer(val register: Register) {


    fun send(activity: LayoutInflater, appCompatActivity: AppCompatActivity){
        BackgroundMail.newBuilder(activity.context)
            .withUsername("piscineNetPtut@gmail.com")
            .withPassword("piscineNetPtut]]")
            .withMailto(this.register.mail.toString())
            .withType(BackgroundMail.TYPE_PLAIN)
            .withSubject("Identifiant PiscineNet")
            .withBody(getBodyOfMail())
            .withOnSuccessCallback {
                (appCompatActivity as ManagementCustomerActivity).onUserWantToAddNewCustomer()
                createNewRegisterOfConnection(appCompatActivity)
            }
            .send()
    }

    private fun getPassword(){
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var passWord = ""
        for (i in 0..8) {
            passWord += chars[floor(Math.random() * chars.length).toInt()]
        }
        this.register.password = passWord
    }

    private fun getBodyOfMail(): String {
        getPassword()
        return "Votre identifiant est : " + this.register.login + '\r' + "Votre mot de passe est : " + this.register.password
    }

    private fun createNewRegisterOfConnection(appCompatActivity: AppCompatActivity) {
        val requestHttp = httpRequest()
        val json = Json(JsonConfiguration.Stable)

        Fuel.post(requestHttp.url+"Register")
            .body(requestHttp.convertData(json.stringify(Register.serializer(), this.register)))
            .header("Content-Type" to "application/x-www-form-urlencoded")
            .responseString { _, _, result ->
                result.fold({
                    appCompatActivity.onBackPressed()
                }, { err ->
                    println(err.message)
                })
            }
    }
}