package fr.iut.piscinenetptut.ui.workingmethod

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fr.iut.piscinenetptut.R
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
            }

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

}