package fr.iut.piscinenetptut.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.addCustomer.AddCustomerActivity
import fr.iut.piscinenetptut.ui.listOfUser.ListUserActivity
import fr.iut.piscinenetptut.ui.listOfVisit.ListOfVisitActivity


class HomeActivity: AppCompatActivity(), HomeActivtyMvc.listenners {

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

    lateinit var homeActivityMvcImpl: HomeActivtyMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            homeActivityMvcImpl = HomeActivtyMvcImpl(this, this)
            setContentView(homeActivityMvcImpl.root)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToAddAClient() {
        try {
            this@HomeActivity.finish()
            AddCustomerActivity.start(this)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToSeeAllClient() {
        try {
            this@HomeActivity.finish()
            ListUserActivity.start(this)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWanttoSeeAllVisit() {
        try {
            this@HomeActivity.finish()
            ListOfVisitActivity.start(this)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}