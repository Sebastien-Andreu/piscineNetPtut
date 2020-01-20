package fr.iut.piscinenetptut.ui.workingmethod

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity
import java.lang.Exception

class WorkingMethodActivity : AppCompatActivity(), WorkingMethodActivityMvc.listeners {

    companion object {
        private val TAG:String = "WorkingMethodActivity"

        fun start(context: Context) {
            try {
                context.startActivity(Intent(context, WorkingMethodActivity::class.java))
            }catch (exception: Exception){
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var workingMethodActivityMcvImpl: WorkingMethodActivityMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            workingMethodActivityMcvImpl = WorkingMethodActivityMvcImpl(this, this)
            setContentView(workingMethodActivityMcvImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onBackPressed() {
        this@WorkingMethodActivity.finish()
        ListCustomerActivity.start(this)
    }

    override fun onUserFinishWorking() {
        try{
            this@WorkingMethodActivity.finish()
            ListCustomerActivity.start(this)
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}