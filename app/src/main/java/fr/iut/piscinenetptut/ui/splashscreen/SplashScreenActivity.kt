package fr.iut.piscinenetptut.ui.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity(), SplashScreenActivityMvc.listeners {

    val TAG: String = "SplashScreenActivity"

    lateinit var splashScreenActivityMvcImpl: SplashScreenActivityMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            splashScreenActivityMvcImpl = SplashScreenActivityMvcImpl(this, this)
            setContentView(splashScreenActivityMvcImpl.root)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToLogin() {
        try {
            this@SplashScreenActivity.finish()
            HomeActivity.start(this@SplashScreenActivity)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}
