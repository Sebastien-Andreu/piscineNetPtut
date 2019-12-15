package fr.iut.piscinenetptut.ui.addCustomer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.home.HomeActivity
import fr.iut.piscinenetptut.ui.listOfUser.ListUserActivity

class AddCustomerActivity : AppCompatActivity(), AddCustomerActivityMvc.listeners {

    companion object {
        private val TAG:String = "AddCustomerActivity"

        fun start(context: Context) {
            try {
                context.startActivity(Intent(context, AddCustomerActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var addCustomerActivityMvcImpl: AddCustomerActivityMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            addCustomerActivityMvcImpl = AddCustomerActivityMvcImpl(this, this)
            setContentView(addCustomerActivityMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onBackPressed() {
        this@AddCustomerActivity.finish()
        HomeActivity.start(this)
    }
}
