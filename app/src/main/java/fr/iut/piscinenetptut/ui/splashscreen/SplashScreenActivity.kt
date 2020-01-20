package fr.iut.piscinenetptut.ui.splashscreen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.home.HomeActivity


class SplashScreenActivity : AppCompatActivity(), SplashScreenActivityMvc.listeners {

    val TAG: String = "SplashScreenActivity"

    lateinit var splashScreenActivityMvcImpl: SplashScreenActivityMvcImpl
    lateinit var splashScreenActivityViewModel: SplashScreenActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            splashScreenActivityMvcImpl = SplashScreenActivityMvcImpl(this, this)
            splashScreenActivityViewModel = SplashScreenActivityViewModel()

            splashScreenActivityViewModel.registerCallBack.observe(this, Observer {
                splashScreenActivityMvcImpl.onRegisterInformationIdLoaded(it)
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
