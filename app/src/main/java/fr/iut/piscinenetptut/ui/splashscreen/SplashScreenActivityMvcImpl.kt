package fr.iut.piscinenetptut.ui.splashscreen

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.home.HomeActivity

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
        // link with database
        if (register.login.equals("login") && register.password.equals("password")){
            this@SplashScreenActivityMvcImpl.splashScreenActivity.finish()
            HomeActivity.start(this@SplashScreenActivityMvcImpl.splashScreenActivity)
        } else {
            Toast.makeText(root?.context, "error with login or password", Toast.LENGTH_LONG).show()
        }
    }
}