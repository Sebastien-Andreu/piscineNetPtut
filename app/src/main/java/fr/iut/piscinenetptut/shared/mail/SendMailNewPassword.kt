package fr.iut.piscinenetptut.shared.mail

import androidx.appcompat.app.AppCompatActivity
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class SendMailNewPassword {

    fun send(appCompatActivity: AppCompatActivity, password : String, mail : String){
        BackgroundMail.newBuilder(appCompatActivity.layoutInflater.context)
            .withUsername("piscineNetPtut@gmail.com")
            .withPassword("piscineNetPtut]]")
            .withMailto(mail)
            .withType(BackgroundMail.TYPE_PLAIN)
            .withSubject("Nouveau mot de passe")
            .withBody("Vous avez demandé un nouveau mot de passe, celui-ci est provisoir, n'oubliez pas de la changer: "+password)
            .send()
    }
}