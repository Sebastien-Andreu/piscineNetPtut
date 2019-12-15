package fr.iut.piscinenetptut.ui.customerdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.listOfUser.ListUserActivity
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity

class CustomerDetailsActivity : AppCompatActivity(), CustomerDetailsActivityMvc.listeners {

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
        ListUserActivity.start(this)
    }
}
