package fr.iut.piscinenetptut.ui.workingmethod

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.library.extension.toTreatFor
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
    lateinit var workingMethodActivityViewModel:  WorkingMethodActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            workingMethodActivityMcvImpl = WorkingMethodActivityMvcImpl(this, this)
            workingMethodActivityViewModel = WorkingMethodActivityViewModel()


            workingMethodActivityViewModel.maintenanceCallBack.observe(this, Observer {
                workingMethodActivityMcvImpl.storeMaintenance(it)
            })

            workingMethodActivityViewModel.technicalCallBack.observe(this, Observer {
                workingMethodActivityMcvImpl.storeTechnical(it)
            })

            workingMethodActivityViewModel.observationCallBack.observe(this, Observer {
                workingMethodActivityMcvImpl.storeObservation(it)
            })


            setContentView(workingMethodActivityMcvImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onBackPressed() {
        this@WorkingMethodActivity.finish()
    }

    override fun onUserWantToStoreVisit() {
        workingMethodActivityMcvImpl.storeVisit()
    }

    override fun onUserWantToStoreMaintenance() {
        workingMethodActivityViewModel.onNeedToGetMaintenanceInformation(workingMethodActivityMcvImpl.root!!)
    }

    override fun onUserWantToStoreTechnical() {
        workingMethodActivityViewModel.onNeedToGetTechnicalInformation(workingMethodActivityMcvImpl.root!!)
    }

    override fun onUserWantToStoreObservation() {
        workingMethodActivityViewModel.onNeedToGetObservationInformation(workingMethodActivityMcvImpl.root!!)
    }

    override fun onUserWantToFinishVisit() {
        onUserWantToStoreObservation()
        workingMethodActivityMcvImpl.saveMaintenance()
    }
}