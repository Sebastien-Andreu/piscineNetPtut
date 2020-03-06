package fr.iut.piscinenetptut.ui.workingmethod

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Maintenance
import fr.iut.piscinenetptut.entities.Observation
import fr.iut.piscinenetptut.entities.Technical
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.adapter.ViewPagerAdapter
import fr.iut.piscinenetptut.shared.view.SwipeDisabledViewPager.SwipeDisabledViewPager
import fr.iut.piscinenetptut.ui.workingmethod.maintenancesheet.MaintenanceSheetFragment
import fr.iut.piscinenetptut.ui.workingmethod.observation.ObservationFragment
import fr.iut.piscinenetptut.ui.workingmethod.technicalsheet.TechnicalSheetFragment

class WorkingMethodActivityMvcImpl(
    private val context: Context,
    private val workingMethodActivity: WorkingMethodActivity
): WorkingMethodActivityMvc {

    val TAG: String = "WorkingMethodActivityMcvImpl"

    var root: View? = null

    var maintenance: Maintenance? = null
    var technical: Technical? = null
    var observation: Observation? = null


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




//                root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
//                    override fun onPageSelected(position: Int) {
//                        workingMethodActivity.onUserWantToStoreTechnical()
//                        workingMethodActivity.onUserWantToStoreMaintenance()
////                        if (0 == position){
////                            Toast.makeText(listFragmentForViewPager[0].activity, "Save Technique and Observation", Toast.LENGTH_LONG).show()
////                            workingMethodActivity.onUserWantToStoreTechnical()
////                        }
////                        if (1 == position){
////                            Toast.makeText(listFragmentForViewPager[1].activity, "Save Maintenance and Observation", Toast.LENGTH_LONG).show()
////                            workingMethodActivity.onUserWantToStoreMaintenance()
////                        }
////                        if (2 == position){
////                            Toast.makeText(listFragmentForViewPager[2].activity, "Save Technique and Maintenance", Toast.LENGTH_LONG).show()
////
////                        }
//                    }
//                })
            }

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun storeMaintenance(maintenance: Maintenance) {
        this.maintenance = maintenance
    }

    override fun storeTechnical(technical: Technical) {
        this.technical = technical
    }

    override fun storeObservation(observation: Observation) {
        this.observation = observation

        //TODO
        //add in database

        if (maintenance != null && technical != null){

        }
    }

    override fun showMaintenanceSheet() {
        root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.currentItem = 0
        workingMethodActivity.workingMethodActivityViewModel.onNeedToGetMaintenanceInformation(root!!)
    }

    override fun showTechnicalSheet() {
        root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.currentItem = 1
        workingMethodActivity.workingMethodActivityViewModel.onNeedToGetTechnicalInformation(root!!)
    }

    override fun showObservationSheet() {
        root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)?.currentItem = 2
        workingMethodActivity.workingMethodActivityViewModel.onNeedToGetObservationInformation(root!!)
    }

}