package fr.iut.piscinenetptut.shared.mail

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.ui.home.HomeActivity
import kotlin.math.floor

class SendMail(val customer: Customer) {

    private val register= Register(null,null,null.toString(),null.toString())

    fun send(activity: LayoutInflater, appCompatActivity: AppCompatActivity){
        BackgroundMail.newBuilder(activity.context)
            .withUsername("piscineNetPtut@gmail.com")
            .withPassword("piscineNetPtut]]")
            .withMailto(customer.mail.toString())
            .withType(BackgroundMail.TYPE_PLAIN)
            .withSubject("Identifiant PiscineNet")
            .withBody(getBodyOfMail())
            .withOnSuccessCallback {
                appCompatActivity.finish()
                HomeActivity.start(activity.context)
            }
            .withOnFailCallback {
                appCompatActivity.finish()
                HomeActivity.start(activity.context)
            }
            .send()
    }

    private fun getPassword(): String{
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var passWord = ""
        for (i in 0..8) {
            passWord += chars[floor(Math.random() * chars.length).toInt()]
        }
        register.password = passWord
        return passWord
    }

    private fun getBodyOfMail(): String {
        register.login = (customer.surname.toString()[0]) + "." + customer.name
        return "Votre identifiant est : " + (customer.surname.toString()[0]) + "." + customer.name + '\r' + "Votre mot de passe est : " + getPassword()
    }
}