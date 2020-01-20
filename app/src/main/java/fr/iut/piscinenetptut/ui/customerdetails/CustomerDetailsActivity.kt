package fr.iut.piscinenetptut.ui.customerdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.accountSetting.AccountSettingActivity
import fr.iut.piscinenetptut.ui.managementCustomer.ManagementCustomerActivity
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity

class CustomerDetailsActivity : AppCompatActivity(), CustomerDetailsActivityMvc.Listeners {

    companion object {
        private val TAG:String = "CustomerDetailsActivity"

        fun start(context: Context) {
            try {

                context.startActivity(Intent(context, CustomerDetailsActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var customerDetailsActivityMvcImpl: CustomerDetailsActivityMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = "Customer details"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            customerDetailsActivityMvcImpl = CustomerDetailsActivityMvcImpl(this, this)
            setContentView(customerDetailsActivityMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantStartWork() {
        try {
            this@CustomerDetailsActivity.finish()
            WorkingMethodActivity.start(this)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToUpdateCustomer() {
        try {
            this@CustomerDetailsActivity.finish()
            ManagementCustomerActivity.start(this)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        this@CustomerDetailsActivity.finish()
        ListCustomerActivity.start(this)
    }
}
