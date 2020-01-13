package fr.iut.piscinenetptut.ui.splashscreen

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.ui.home.HomeActivity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class SplashScreenActivityMvcImpl (
    val splashScreenActivity: SplashScreenActivity,
    val context: Context
): SplashScreenActivityMvc {

    val TAG: String = "SplashScreenActivityMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_splashscreen, null)

            if (null != root) {
                root!!.findViewById<Button>(R.id.buttonLogin)?.setOnClickListener {
                    splashScreenActivity.onUserWantToLogin()
                }
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onRegisterInformationIdLoaded(register: Register) {
        val json = Json(JsonConfiguration.Stable)
        val requestHttp = httpRequest()

        Fuel.post(requestHttp.url+"auth")
            .body(requestHttp.convertData(json.stringify(Register.serializer(), register)))
            .header("Content-Type" to "application/x-www-form-urlencoded")
            .responseString { request, response, result ->
                result.fold({ d ->
                    this@SplashScreenActivityMvcImpl.splashScreenActivity.finish()
                    HomeActivity.start(this@SplashScreenActivityMvcImpl.splashScreenActivity)
                }, { err ->
                    Toast.makeText(root?.context, "error with login or password", Toast.LENGTH_LONG).show()
                })
            }
    }
}