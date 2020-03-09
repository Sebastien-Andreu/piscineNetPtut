package fr.iut.piscinenetptut.ui.customerdetails

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Account
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.adapter.ViewPagerAdapter
import fr.iut.piscinenetptut.shared.view.SwipeDisabledViewPager.SwipeDisabledViewPager
import fr.iut.piscinenetptut.ui.customerdetails.customer.CustomerDetailFragment
import fr.iut.piscinenetptut.ui.customerdetails.swimmingpool.SwimmingPoolDetailFragment


class CustomerDetailsActivityMvcImpl(
    private val context: Context,
    private val customerDetailsActivity: CustomerDetailsActivity
): CustomerDetailsActivityMvc {

    val TAG: String = "CustomerDetailsActivityMvcImpl"

    var root: View? = null

    lateinit var listFragmentForViewPager: ArrayList<Fragment>

    init {
        try {
            root = View.inflate(context, R.layout.activity_customer_details, null)

            if (Account.register.role == "customer"){
                CustomerSelected.customer.ID = Account.register.ID_Customer!!.toInt()
                CustomerSelected.pool.ID_Customer = Account.register.ID_Customer!!.toInt()
            }

            listFragmentForViewPager = arrayListOf(CustomerDetailFragment(), SwimmingPoolDetailFragment())
            val listFragmentTitleForViewPager: ArrayList<String> = arrayListOf("Client", "Piscine")

            if (null != root) {
                root!!.findViewById<SwipeDisabledViewPager>(R.id.customerDetailsViewPager)?.let {viewPager ->
                    viewPager.adapter = ViewPagerAdapter(customerDetailsActivity.supportFragmentManager, listFragmentForViewPager, listFragmentTitleForViewPager)
                    root!!.findViewById<TabLayout>(R.id.customerDetailsTabLayout)?.setupWithViewPager(viewPager)
                }
            }

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}