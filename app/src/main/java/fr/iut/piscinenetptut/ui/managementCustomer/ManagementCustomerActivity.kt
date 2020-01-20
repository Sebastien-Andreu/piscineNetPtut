package fr.iut.piscinenetptut.ui.managementCustomer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.home.HomeActivity
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity


class ManagementCustomerActivity : AppCompatActivity(), ManagementCustomerActivityMvc.Listeners {

    companion object {
        private val TAG: String = "ManagementCustomerActivity"

        fun start(context: Context) {
            try {
                context.startActivity(Intent(context, ManagementCustomerActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var managementCustomerActivityMvcImpl: ManagementCustomerActivityMvcImpl
    lateinit var managementCustomerActivityViewModel: ManagementCustomerActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = "Add new customer"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)


            if (CustomerSelected.customer.ID != null){
                supportActionBar?.title = "Update information"
            }

            managementCustomerActivityMvcImpl = ManagementCustomerActivityMvcImpl(this, this)
            managementCustomerActivityViewModel = ManagementCustomerActivityViewModel()

            setContentView(managementCustomerActivityMvcImpl.root)

            managementCustomerActivityViewModel.addCustomerCallBack.observe(this, Observer {
                managementCustomerActivityMvcImpl.addCustomer(it)
            })

            managementCustomerActivityViewModel.addPoolCallBack.observe(this, Observer {
                managementCustomerActivityMvcImpl.addPool(it)
            })

            managementCustomerActivityViewModel.updateCustomerCallBack.observe(this, Observer {
                managementCustomerActivityMvcImpl.updateCustomer()
            })

            managementCustomerActivityViewModel.updatePoolCallBack.observe(this, Observer {
                managementCustomerActivityMvcImpl.updatePool()
            })

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        this@ManagementCustomerActivity.finish()
        if (CustomerSelected.customer.ID != null){
            ListCustomerActivity.start(this)
        }else {
            HomeActivity.start(this)
        }
    }

    override fun onUserWantToAddNewCustomer() {
        managementCustomerActivityViewModel.onNeedToGetCustomerInformation(managementCustomerActivityMvcImpl.root!!)
    }

    override fun onUserWantToAddNewPool(id_Customer: Int?) {
        managementCustomerActivityViewModel.onNeedToGetPoolInformation(managementCustomerActivityMvcImpl.root!!, id_Customer)
    }

    override fun onUserWantToUpdateCustomer() {
        managementCustomerActivityViewModel.onNeedToGetCustomerInformation(managementCustomerActivityMvcImpl.root!!)
    }

    override fun onUserWantToUpdatePool() {
        managementCustomerActivityViewModel.onNeedToGetPoolInformation(managementCustomerActivityMvcImpl.root!!, picture = CustomerSelected.pool.picture)
    }
}
