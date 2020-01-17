package fr.iut.piscinenetptut.ui.managementCustomer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.home.HomeActivity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class ManagementCustomerActivity : AppCompatActivity(), ManagementCustomerActivityMvc.listeners {

    companion object {
        private val TAG: String = "ManagementCustomerActivity"

        private val EXTRA_CUSTOMER_DETAIL: String = "EXTRA_CUSTOMER_DETAIL"
        private val EXTRA_POOL_DETAIL: String = "EXTRA_POOL_DETAIL"

        val json = Json(JsonConfiguration.Stable)

        fun start(
            context: Context,
            customer: Customer? = null,
            pool: Pool?= null
        ) {
            try {
                if (customer != null && pool != null){
                    context.startActivity(Intent(context, ManagementCustomerActivity::class.java)
                        .putExtra(EXTRA_CUSTOMER_DETAIL, json.stringify(Customer.serializer(), customer))
                        .putExtra(EXTRA_POOL_DETAIL, json.stringify(Pool.serializer(), pool)))
                } else {
                    context.startActivity(Intent(context, ManagementCustomerActivity::class.java))
                }

            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var managementCustomerActivityMvcImpl: ManagementCustomerActivityMvcImpl
    lateinit var managementCustomerActivityViewModel: ManagementCustomerActivityViewModel

    var customer: Customer?= null
    var pool: Pool?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            if (intent.getStringExtra(EXTRA_CUSTOMER_DETAIL) != null && intent.getStringExtra(EXTRA_POOL_DETAIL) != null ){
                this.customer = json.parse(Customer.serializer(),intent.getStringExtra(EXTRA_CUSTOMER_DETAIL)!!)
                this.pool = json.parse(Pool.serializer(),intent.getStringExtra(EXTRA_POOL_DETAIL)!!)
            }

            managementCustomerActivityMvcImpl = ManagementCustomerActivityMvcImpl(this, this)
            managementCustomerActivityViewModel = ManagementCustomerActivityViewModel()

            setContentView(managementCustomerActivityMvcImpl.root)

            managementCustomerActivityViewModel.customerCallBack.observe(this, Observer {
                managementCustomerActivityMvcImpl.onCustomerInformationIsLoaded(it)
            })

            managementCustomerActivityViewModel.poolCallBack.observe(this, Observer {
                managementCustomerActivityMvcImpl.onPoolInformationIsLoaded(it)
            })

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onBackPressed() {
        this@ManagementCustomerActivity.finish()
        HomeActivity.start(this)
    }

    override fun onUserWantToAddNewCustomer() {
        if (pool != null) {
            managementCustomerActivityViewModel.onNeedToGetCustomerInformation(managementCustomerActivityMvcImpl.root!!, customer!!.ID)
        }else {
            managementCustomerActivityViewModel.onNeedToGetCustomerInformation(managementCustomerActivityMvcImpl.root!!)
        }
    }

    override fun onUserWantToAddNewPool(id_Customer: Int?) {
        if (pool != null){
            println("------------------------------------------------------1")
            managementCustomerActivityViewModel.onNeedToGetPoolInformation(managementCustomerActivityMvcImpl.root!!, customer!!.ID, pool!!.picture)
        } else {
            managementCustomerActivityViewModel.onNeedToGetPoolInformation(managementCustomerActivityMvcImpl.root!!, id_Customer)
        }
    }
}
