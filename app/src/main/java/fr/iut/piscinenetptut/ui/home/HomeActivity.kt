package fr.iut.piscinenetptut.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.setAppLocale
import fr.iut.piscinenetptut.library.extension.toTreatFor


class HomeActivity: AppCompatActivity() {

    companion object {
        private val TAG: String = "HomeActivity"

        fun start(context: Context) {
            try {
                context.startActivity(Intent(context, HomeActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    private lateinit var homeActivityMvcImpl: HomeActivityMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            CustomerSelected.reset()

            homeActivityMvcImpl = HomeActivityMvcImpl(this, this)
            setContentView(homeActivityMvcImpl.root)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onBackPressed() {
        //Nothing
    }
}