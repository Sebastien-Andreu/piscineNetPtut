package fr.iut.piscinenetptut.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.accountSetting.AccountSettingActivity
import fr.iut.piscinenetptut.ui.managementCustomer.ManagementCustomerActivity
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity
import fr.iut.piscinenetptut.ui.listOfVisit.ListOfVisitActivity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration


class HomeActivity: AppCompatActivity(), HomeActivtyMvc.listenners {

    companion object {
        private val TAG: String = "HomeActivity"

        private val EXTRA_REGISTER_DETAIL: String = "EXTRA_REGISTER_DETAIL"

        val json = Json(JsonConfiguration.Stable)

        fun start(
            context: Context,
            register: Register
        ) {
            try {
                context.startActivity(Intent(context, HomeActivity::class.java)
                    .putExtra(EXTRA_REGISTER_DETAIL, json.stringify(Register.serializer(), register)))


            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var homeActivityMvcImpl: HomeActivtyMvcImpl
    lateinit var register: Register

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            this.register = json.parse(Register.serializer(),intent.getStringExtra(EXTRA_REGISTER_DETAIL)!!)

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
                AccountSettingActivity.start(this, register)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onUserWantToAddAClient() {
        try {
            this@HomeActivity.finish()
            ManagementCustomerActivity.start(this, register)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToSeeAllClient() {
        try {
            this@HomeActivity.finish()
            ListCustomerActivity.start(this, register)
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