package fr.iut.piscinenetptut.ui.addCustomer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.home.HomeActivity

class AddCustomerActivity : AppCompatActivity(), AddCustomerActivityMvc.listeners {

    companion object {
        private val TAG: String = "AddCustomerActivity"

        fun start(context: Context) {
            try {
                context.startActivity(Intent(context, AddCustomerActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var addCustomerActivityMvcImpl: AddCustomerActivityMvcImpl
    lateinit var addCustomerActivityViewModel: AddCustomerActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            addCustomerActivityMvcImpl = AddCustomerActivityMvcImpl(this, this)
            addCustomerActivityViewModel = AddCustomerActivityViewModel()

            setContentView(addCustomerActivityMvcImpl.root)

            addCustomerActivityViewModel.customerCallBack.observe(this, Observer {
                addCustomerActivityMvcImpl.onCustomerInformationIsLoaded(it)
            })

            addCustomerActivityViewModel.poolCallBack.observe(this, Observer {
                addCustomerActivityMvcImpl.onPoolInformationIsLoaded(it)
            })

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onBackPressed() {
        this@AddCustomerActivity.finish()
        HomeActivity.start(this)
    }

    override fun onUserWantToAddNewCustomer() {
        addCustomerActivityViewModel.onNeedToGetCustomerInformation(addCustomerActivityMvcImpl.root)
    }

    override fun onUserWantToAddNewPool(id_Customer: Int?) {
        addCustomerActivityViewModel.onNeedToGetPoolInformation(addCustomerActivityMvcImpl.root, id_Customer)
    }
}
