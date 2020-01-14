package fr.iut.piscinenetptut.ui.customerdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.json
import kotlinx.serialization.parse

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

    override fun onBackPressed() {
        this@CustomerDetailsActivity.finish()
        ListCustomerActivity.start(this)
    }
}
