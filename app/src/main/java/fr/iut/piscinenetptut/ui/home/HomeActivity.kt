package fr.iut.piscinenetptut.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.accountSetting.AccountSettingActivity
import fr.iut.piscinenetptut.ui.managementCustomer.ManagementCustomerActivity
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity
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

            CustomerSelected.reset()

            homeActivityMvcImpl = HomeActivtyMvcImpl(this, this)
            setContentView(homeActivityMvcImpl.root)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_setting -> {
                AccountSettingActivity.start(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onUserWantToAddAClient() {
        try {
            this@HomeActivity.finish()
            ManagementCustomerActivity.start(this)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToSeeAllClient() {
        try {
            this@HomeActivity.finish()
            ListCustomerActivity.start(this)
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