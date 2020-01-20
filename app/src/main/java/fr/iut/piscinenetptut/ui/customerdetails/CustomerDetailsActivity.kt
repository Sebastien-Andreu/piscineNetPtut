package fr.iut.piscinenetptut.ui.customerdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.accountSetting.AccountSettingActivity
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
        private val EXTRA_REGISTER_DETAIL: String = "EXTRA_REGISTER_DETAIL"

        val json = Json(JsonConfiguration.Stable)

        fun start(
            context: Context,
            customer: Customer,
            pool: Pool,
            register: Register
        ) {
            try {

                context.startActivity(Intent(context, CustomerDetailsActivity::class.java)
                    .putExtra(EXTRA_CUSTOMER_DETAIL, json.stringify(Customer.serializer(), customer))
                    .putExtra(EXTRA_POOL_DETAIL, json.stringify(Pool.serializer(), pool))
                    .putExtra(EXTRA_REGISTER_DETAIL, json.stringify(Register.serializer(), register)))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var customerDetailsActivityMvcImpl: CustomerDetailsActivityMvcImpl
    lateinit var customer: Customer
    lateinit var pool: Pool
    lateinit var register: Register

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = "Customer details"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            this.customer = json.parse(Customer.serializer(),intent.getStringExtra(EXTRA_CUSTOMER_DETAIL)!!)
            this.pool = json.parse(Pool.serializer(),intent.getStringExtra(EXTRA_POOL_DETAIL)!!)
            this.register = json.parse(Register.serializer(),intent.getStringExtra(EXTRA_REGISTER_DETAIL)!!)


            customerDetailsActivityMvcImpl = CustomerDetailsActivityMvcImpl(this, this)
            setContentView(customerDetailsActivityMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantStartWork() {
        try {
            this@CustomerDetailsActivity.finish()
            WorkingMethodActivity.start(this, register)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToUpdateCustomer() {
        try {
            this@CustomerDetailsActivity.finish()
            ManagementCustomerActivity.start(this,register, customer, pool)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        this@CustomerDetailsActivity.finish()
        ListCustomerActivity.start(this, register)
    }
}
