package fr.iut.piscinenetptut.ui.workingmethod

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.kittinunf.fuel.Fuel
import com.google.android.material.tabs.TabLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.*
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.adapter.ViewPagerAdapter
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.shared.view.SwipeDisabledViewPager.SwipeDisabledViewPager
import fr.iut.piscinenetptut.ui.workingmethod.maintenancesheet.MaintenanceSheetFragment
import fr.iut.piscinenetptut.ui.workingmethod.observation.ObservationFragment
import fr.iut.piscinenetptut.ui.workingmethod.technicalsheet.TechnicalSheetFragment
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class WorkingMethodActivityMvcImpl(
    private val context: Context,
    private val workingMethodActivity: WorkingMethodActivity
): WorkingMethodActivityMvc {

    val TAG: String = "WorkingMethodActivityMcvImpl"

    var root: View? = null

    var maintenance: Maintenance? = null
    var technical: Technical? = null
    var observation: Observation? = null
    var visit: Visit?= null

    var idSave : Int = 0

    private val json = Json(JsonConfiguration.Stable)
    private val requestHttp = httpRequest()

    init {
        try {
            root = View.inflate(context, R.layout.activity_working_method, null)


            val listFragmentForViewPager: ArrayList<Fragment> = arrayListOf(MaintenanceSheetFragment(), TechnicalSheetFragment(), ObservationFragment())
            val listFragmentTitleForViewPager: ArrayList<String> = arrayListOf("Maintenance", "Technique", "Observation")

            if (null != root) {
                root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.let { viewPager ->
                    viewPager.adapter = ViewPagerAdapter(workingMethodActivity.supportFragmentManager, listFragmentForViewPager, listFragmentTitleForViewPager)
                    root!!.findViewById<TabLayout>(R.id.workingMethodTabLayout)?.setupWithViewPager(viewPager)
                }

                this.idSave = 0

                root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
                    override fun onPageSelected(position: Int) {
                        root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.currentItem = idSave
                        root!!.findViewById<TabLayout>(R.id.workingMethodTabLayout)?.setScrollPosition(idSave,0f,true)
                        if (idSave != position){
                            saveWork(position)
                        }
                    }
                })
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    private fun saveWork(position: Int){
        val builder = workingMethodActivity.let { it1 -> AlertDialog.Builder(it1) }

        builder.setTitle("Save !")
        builder.setPositiveButton("YES"){_, _ ->
            when (this.idSave) {
                0 -> {
                    workingMethodActivity.onUserWantToStoreMaintenance()
                    this.idSave = position
                    root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.currentItem = position
                    root!!.findViewById<TabLayout>(R.id.workingMethodTabLayout)?.setScrollPosition(position,0f,true)
                }
                1 -> {
                    workingMethodActivity.onUserWantToStoreTechnical()
                    this.idSave = position
                    root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.currentItem = position
                    root!!.findViewById<TabLayout>(R.id.workingMethodTabLayout)?.setScrollPosition(position,0f,true)
                }
                2 -> {
                    workingMethodActivity.onUserWantToStoreObservation()
                    this.idSave = position
                    root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.currentItem = position
                    root!!.findViewById<TabLayout>(R.id.workingMethodTabLayout)?.setScrollPosition(position,0f,true)
                }
            }
        }
        builder.setNegativeButton("No"){_, _ ->
            root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.currentItem = this.idSave
            root!!.findViewById<TabLayout>(R.id.workingMethodTabLayout)?.setScrollPosition(this.idSave,0f,true)
        }

        val dialog: AlertDialog? = builder.create()
        dialog?.show()
    }


    override fun storeMaintenance(maintenance: Maintenance) {
        this.maintenance = maintenance
    }

    override fun storeTechnical(technical: Technical) {
        this.technical = technical

    }

    override fun storeObservation(observation: Observation) {
        this.observation = observation

    }

    override fun storeVisit() {
        if (maintenance != null && technical != null){
            visit = Visit(
                NameCustomer = (CustomerSelected.customer.surname + " " + CustomerSelected.customer.name),
                NameEmployee = Account.register.login, // request get name with ID Register
                ID_Maintenance = this.maintenance!!.ID.toString(), // last id maintenance
                ID_Observation = this.observation!!.ID.toString(), // last id observation
                ID_Technical = this.technical!!.ID.toString() // last id technical
            )
            saveVisit()
        }
    }

    override fun saveMaintenance() {
        try {
            if (null != root && this.maintenance != null) {
                Fuel.post(requestHttp.url+"Maintenance")
                    .body(requestHttp.convertData(json.stringify(Maintenance.serializer(), this.maintenance!!)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({ d ->
                            this.maintenance = json.parse(Maintenance.serializer(), d)
                            saveTechnical()
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun saveTechnical() {
        try {
            if (null != root && this.technical != null) {
                Fuel.post(requestHttp.url+"Technical")
                    .body(requestHttp.convertData(json.stringify(Technical.serializer(), this.technical!!)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({d ->
                            this.technical = json.parse(Technical.serializer(), d)
                            saveObservation()
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun saveObservation() {
        try {
            if (null != root && this.observation != null) {
                Fuel.post(requestHttp.url+"Observation")
                    .body(requestHttp.convertData(json.stringify(Observation.serializer(), this.observation!!)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({ d ->
                            this.observation = json.parse(Observation.serializer(), d)
                            storeVisit()
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun saveVisit(){
        try {
            if (null != root) {
                Fuel.post(requestHttp.url+"Visit")
                    .body(requestHttp.convertData(json.stringify(Visit.serializer(), this.visit!!)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({
                            workingMethodActivity.onBackPressed()
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}