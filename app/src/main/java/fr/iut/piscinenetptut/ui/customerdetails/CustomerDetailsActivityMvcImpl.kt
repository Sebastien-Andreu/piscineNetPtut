package fr.iut.piscinenetptut.ui.customerdetails

import android.app.ActionBar
import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.adapter.ViewPagerAdapter
import fr.iut.piscinenetptut.shared.view.SwipeDisabledViewPager.SwipeDisabledViewPager
import fr.iut.piscinenetptut.ui.customerdetails.customer.CustomerFragment
import fr.iut.piscinenetptut.ui.customerdetails.swimmingpool.SwimmingPoolFragment

class CustomerDetailsActivityMvcImpl(
    private val context: Context,
    private val customerDetailsActivity: CustomerDetailsActivity
): CustomerDetailsActivityMvc {

    val TAG: String = "CustomerDetailsActivityMvcImpl"

    lateinit var rootView: View

    init {
        try {
            rootView = View.inflate(context, R.layout.activity_customer_details, null)

            customerDetailsActivity.actionBar?.hide()

            val listFragmentForViewPager: ArrayList<Fragment> = arrayListOf(CustomerFragment(), SwimmingPoolFragment())
            val listFragmentTitleForViewPager: ArrayList<String> = arrayListOf("Client", "Piscine")

            rootView.findViewById<SwipeDisabledViewPager>(R.id.customerDetailsViewPager)?.let {viewPager ->
                viewPager.adapter = ViewPagerAdapter(customerDetailsActivity.supportFragmentManager, listFragmentForViewPager, listFragmentTitleForViewPager)
                rootView.findViewById<TabLayout>(R.id.customerDetailsTabLayout)?.setupWithViewPager(viewPager)
            }


        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}