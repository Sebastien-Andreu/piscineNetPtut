package fr.iut.piscinenetptut.ui.visitDetails

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.adapter.ViewPagerAdapter
import fr.iut.piscinenetptut.shared.view.SwipeDisabledViewPager.SwipeDisabledViewPager
import fr.iut.piscinenetptut.ui.visitDetails.maintenanceSheet.MaintenanceSheetFragment
import fr.iut.piscinenetptut.ui.visitDetails.observation.ObservationFragment
import fr.iut.piscinenetptut.ui.visitDetails.technicalsheet.TechnicalSheetFragment

class VisitDetailsMvcImpl(
    private val context: Context,
    private val visitDetailsActivity: VisitDetailsActivity
): VisitDetailsMvc {

    val TAG: String = "VisitDetailsMvcImpl"

    var root: View? = null

    init {
        try {

            root = View.inflate(context, R.layout.activity_working_method, null)


            val listFragmentForViewPager: ArrayList<Fragment> = arrayListOf(MaintenanceSheetFragment(), TechnicalSheetFragment(), ObservationFragment())
            val listFragmentTitleForViewPager: ArrayList<String> = arrayListOf(context.getString(R.string.Maintenance), context.getString(R.string.Technique),context.getString(R.string.Observation))

            if (null != root) {
                root!!.findViewById<SwipeDisabledViewPager>(R.id.workingMethodViewPager)
                    ?.let { viewPager ->
                        viewPager.adapter = ViewPagerAdapter(visitDetailsActivity.supportFragmentManager, listFragmentForViewPager, listFragmentTitleForViewPager
                        )
                        root!!.findViewById<TabLayout>(R.id.workingMethodTabLayout)?.setupWithViewPager(viewPager)
                    }
            }
        } catch (exception : Exception){
            exception.toTreatFor(TAG)
        }
    }
}