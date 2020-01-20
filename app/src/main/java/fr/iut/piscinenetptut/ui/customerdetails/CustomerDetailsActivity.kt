package fr.iut.piscinenetptut.ui.customerdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.managementCustomer.ManagementCustomerActivity
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class CustomerDetailsActivity : AppCompatActivity(), CustomerDetailsActivityMvc.listeners {

    companion object {
        private val TAG:String = "CustomerDetailsActivity"

        private val EXTRA_CUSTOMER_DETAIL: String = "EXTRA_CUSTOMER_DETAIL"
        private val EXTRA_POOL_DETAIL: String = "EXTRA_POOL_DETAIL"

        val json = Json(JsonConfiguration.Stable)

        fun start(
            context: Context,
            customer: Customer,
            pool: Pool
        ) {
            try {

                context.startActivity(Intent(context, CustomerDetailsActivity::class.java)
                    .putExtra(EXTRA_CUSTOMER_DETAIL, json.stringify(Customer.serializer(), customer))
                    .putExtra(EXTRA_POOL_DETAIL, json.stringify(Pool.serializer(), pool)))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var customerDetailsActivityMvcImpl: CustomerDetailsActivityMvcImpl
    lateinit var customer: Customer
    lateinit var pool: Pool

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = "Customer details"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            this.customer = json.parse(Customer.serializer(),intent.getStringExtra(EXTRA_CUSTOMER_DETAIL)!!)
            this.pool = json.parse(Pool.serializer(),intent.getStringExtra(EXTRA_POOL_DETAIL)!!)


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
            ManagementCustomerActivity.start(this, customer, pool)
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
                Toast.makeText(applicationContext, "setting", Toast.LENGTH_LONG).show()
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
