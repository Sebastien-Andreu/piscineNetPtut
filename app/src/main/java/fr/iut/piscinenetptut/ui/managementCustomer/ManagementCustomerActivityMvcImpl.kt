package fr.iut.piscinenetptut.ui.managementCustomer

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FileDataPart
import com.google.android.material.tabs.TabLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.adapter.ViewPagerAdapter
import fr.iut.piscinenetptut.shared.mail.SendMail
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.shared.view.SwipeDisabledViewPager.SwipeDisabledViewPager
import fr.iut.piscinenetptut.ui.managementCustomer.customer.CustomerFragment
import fr.iut.piscinenetptut.ui.managementCustomer.swimmingpool.SwimmingPoolFragment
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File


class ManagementCustomerActivityMvcImpl(
    private val context: Context,
    private val managementCustomerActivity: ManagementCustomerActivity
): ManagementCustomerActivityMvc {

    val TAG: String = "ManagementCustomerActivityMvc"

    private val json = Json(JsonConfiguration.Stable)
    private val requestHttp = httpRequest()

    private var customer: Customer? = null

    var root: View? = null

    lateinit var listFragmentForViewPager: ArrayList<Fragment>

    init {
        try {
            root = View.inflate(context, R.layout.activity_add_customer, null)

            listFragmentForViewPager = arrayListOf(CustomerFragment(), SwimmingPoolFragment())
            val listFragmentTitleForViewPager: ArrayList<String> = arrayListOf("Client", "Piscine")

            if (null != root) {
                root!!.findViewById<SwipeDisabledViewPager>(R.id.addCustomerViewPager)?.let {viewPager ->
                    viewPager.adapter = ViewPagerAdapter(managementCustomerActivity.supportFragmentManager, listFragmentForViewPager, listFragmentTitleForViewPager)
                    root!!.findViewById<TabLayout>(R.id.addCustomerTabLayout)?.setupWithViewPager(viewPager)
                }

                root!!.findViewById<SwipeDisabledViewPager>(R.id.addCustomerViewPager)?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
                    override fun onPageSelected(position: Int) {
                        if (1 == position){
                            if (!(listFragmentForViewPager[0] as CustomerFragment).onUserWantToVerifyAlInput()){
                                Toast.makeText(listFragmentForViewPager[0].activity, "Some information is missing to access the pool addition", Toast.LENGTH_LONG).show()
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


    override fun addCustomer(customer: Customer) {
        try {
            if (null != root) {
                this.customer = customer
                Fuel.post(requestHttp.url+"Customer")
                    .body(requestHttp.convertData(json.stringify(Customer.serializer(), customer)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({ d ->
                            managementCustomerActivity.onUserWantToAddNewPool(json.parse(Customer.serializer(), d).ID)
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun addPool(pool: Pool) {
        try {
            if (null != root) {

                val file : String? = (listFragmentForViewPager[1] as SwimmingPoolFragment).uriPicture

                if (file != null){
                    Fuel.upload(requestHttp.url + "Picture").add{ FileDataPart(File(file), name = "picture", filename=pool.picture) }
                        .response { result ->
                            println(result)
                        }
                }

                Fuel.post(requestHttp.url+"Pool")
                    .body(requestHttp.convertData(json.stringify(Pool.serializer(), pool)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({
                            val mail = SendMail(customer!!)
                            mail.send(managementCustomerActivity.layoutInflater, managementCustomerActivity)
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun updateCustomer() {
        try {
            if (null != root) {

                Fuel.put(requestHttp.url+"Customer/" + CustomerSelected.customer.ID)
                    .body(requestHttp.convertData(json.stringify(Customer.serializer(), CustomerSelected.customer)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({
                            Toast.makeText(listFragmentForViewPager[0].activity, "Updated !", Toast.LENGTH_LONG).show()
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun updatePool() {
        try {
            if (null != root) {

                val file : String? = (listFragmentForViewPager[1] as SwimmingPoolFragment).uriPicture

                if (file != null){
                    Fuel.upload(requestHttp.url + "Picture").add{ FileDataPart(File(file), name = "picture", filename=CustomerSelected.pool.picture) }
                        .response { result ->
                            println(result)
                        }
                }

                Fuel.put(requestHttp.url+"Pool/" + CustomerSelected.pool.ID_Customer)
                    .body(requestHttp.convertData(json.stringify(Pool.serializer(), CustomerSelected.pool)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({
                            Toast.makeText(listFragmentForViewPager[1].activity, "Updated !", Toast.LENGTH_LONG).show()
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