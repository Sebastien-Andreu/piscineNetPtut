package fr.iut.piscinenetptut.ui.customerdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.iut.piscinenetptut.library.extension.toTreatFor

class CustomerDetailsActivity : AppCompatActivity() {

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
            setContentView(customerDetailsActivityMvcImpl.rootView)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}
