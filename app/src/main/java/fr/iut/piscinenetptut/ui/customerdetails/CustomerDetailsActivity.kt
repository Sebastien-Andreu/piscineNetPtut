package fr.iut.piscinenetptut.ui.customerdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.setAppLocale
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.home.HomeActivity
import fr.iut.piscinenetptut.ui.managementCustomer.ManagementCustomerActivity

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

    private lateinit var customerDetailsActivityMvcImpl: CustomerDetailsActivityMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = getString(R.string.CustomerDetails)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            customerDetailsActivityMvcImpl = CustomerDetailsActivityMvcImpl(this, this)
            setContentView(customerDetailsActivityMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToUpdateCustomer() {
        try {
            ManagementCustomerActivity.start(this)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        this@CustomerDetailsActivity.finish()
    }
}
