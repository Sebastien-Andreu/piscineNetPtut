package fr.iut.piscinenetptut.ui.addCustomer

import android.content.Context
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.kittinunf.fuel.Fuel
import com.google.android.material.tabs.TabLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.adapter.ViewPagerAdapter
import fr.iut.piscinenetptut.shared.view.SwipeDisabledViewPager.SwipeDisabledViewPager
import fr.iut.piscinenetptut.ui.addCustomer.customer.CustomerFragment
import fr.iut.piscinenetptut.ui.addCustomer.swimmingpool.SwimmingPoolFragment

import kotlinx.serialization.json.*

class AddCustomerActivityMvcImpl(
    private val context: Context,
    private val addCustomerActivity: AddCustomerActivity
): AddCustomerActivityMvc {

    val TAG: String = "AddCustomerActivityMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_add_customer, null)

            addCustomerActivity.actionBar?.hide()

            val listFragmentForViewPager: ArrayList<Fragment> = arrayListOf(CustomerFragment(), SwimmingPoolFragment())
            val listFragmentTitleForViewPager: ArrayList<String> = arrayListOf("Client", "Piscine")

            if (null != root) {
                root!!.findViewById<SwipeDisabledViewPager>(R.id.addCustomerViewPager)?.let {viewPager ->
                    viewPager.adapter = ViewPagerAdapter(addCustomerActivity.supportFragmentManager, listFragmentForViewPager, listFragmentTitleForViewPager)
                    root!!.findViewById<TabLayout>(R.id.addCustomerTabLayout)?.setupWithViewPager(viewPager)
                }

                root!!.findViewById<SwipeDisabledViewPager>(R.id.addCustomerViewPager)?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
                    override fun onPageSelected(position: Int) {
                        if (1 == position){
                            if (!(listFragmentForViewPager[0] as CustomerFragment).onUserWantToVerifyAlInput()){
                                Toast.makeText(listFragmentForViewPager[0].activity, "pas bon", Toast.LENGTH_LONG).show()
                                root!!.findViewById<SwipeDisabledViewPager>(R.id.addCustomerViewPager)?.currentItem = 0
                                root!!.findViewById<TabLayout>(R.id.addCustomerTabLayout)?.setScrollPosition(0,0f,true)
                            }
                        }
                    }
                })


            }

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onCustomerInformationIsLoaded(customer: Customer) {
        try {
            if (null != root) {

                val json = Json(JsonConfiguration.Stable)
                val jsonData = json.stringify(Customer.serializer(), customer).replace("\"", "")
                                                                              .replace("{","")
                                                                              .replace("}", "")
                                                                              .replace(":", "=")
                                                                              .replace(",", "&")

                Fuel.post("https://piscinenetptut1.tunnel.datahub.at/Customer")
                    .body(jsonData)
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { request, response, result ->
                        result.fold({ d ->
                            println(d)
                            //addCustomerActivity.onUserWantToAddNewPool(ID)
                        }, { err ->
                            println(err.message)
                        })
                    }

            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun onPoolInformationIsLoaded(pool: Pool){
        try {
            if (null != root){
                println(pool)
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}