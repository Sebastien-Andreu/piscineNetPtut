package fr.iut.piscinenetptut.ui.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.library.extension.toTreatFor


class SplashScreenActivity : AppCompatActivity(), SplashScreenActivityMvc.Listeners {

    val TAG: String = "SplashScreenActivity"

    companion object {
        val TAG: String = "SplashScreenActivity"
        fun start(context: Context) {
            try {
                context.startActivity(Intent(context, SplashScreenActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var splashScreenActivityMvcImpl: SplashScreenActivityMvcImpl
    lateinit var splashScreenActivityViewModel: SplashScreenActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            splashScreenActivityMvcImpl = SplashScreenActivityMvcImpl(this, this)
            splashScreenActivityViewModel = SplashScreenActivityViewModel()

            splashScreenActivityViewModel.registerCallBack.observe(this, Observer {
                splashScreenActivityMvcImpl.onRegisterInformationIdLoaded()
            })
            setContentView(splashScreenActivityMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToLogin() {
        try {
            splashScreenActivityViewModel.onNeedToGetCustomerInformation(splashScreenActivityMvcImpl.root!!)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}
