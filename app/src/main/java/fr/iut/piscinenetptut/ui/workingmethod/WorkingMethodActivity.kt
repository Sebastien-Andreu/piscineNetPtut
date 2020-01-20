package fr.iut.piscinenetptut.ui.workingmethod

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.lang.Exception

class WorkingMethodActivity : AppCompatActivity(), WorkingMethodActivityMvc.listeners {

    companion object {
        private val TAG:String = "WorkingMethodActivity"

        private val EXTRA_REGISTER_DETAIL: String = "EXTRA_REGISTER_DETAIL"

        val json = Json(JsonConfiguration.Stable)

        fun start(
            context: Context,
            register: Register) {
            try {
                context.startActivity(Intent(context, WorkingMethodActivity::class.java)
                    .putExtra(EXTRA_REGISTER_DETAIL, json.stringify(Register.serializer(), register)))
            }catch (exception: Exception){
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var workingMethodActivityMcvImpl: WorkingMethodActivityMvcImpl
    lateinit var register: Register

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            this.register = json.parse(Register.serializer(),intent.getStringExtra(EXTRA_REGISTER_DETAIL)!!)

            workingMethodActivityMcvImpl = WorkingMethodActivityMvcImpl(this, this)
            setContentView(workingMethodActivityMcvImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onBackPressed() {
        this@WorkingMethodActivity.finish()
        ListCustomerActivity.start(this, register)
    }

    override fun onUserFinishWorking() {
        try{
            this@WorkingMethodActivity.finish()
            ListCustomerActivity.start(this, register)
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}