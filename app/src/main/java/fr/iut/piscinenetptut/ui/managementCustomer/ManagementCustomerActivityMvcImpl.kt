package fr.iut.piscinenetptut.ui.managementCustomer

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FileDataPart
import com.google.android.material.tabs.TabLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.adapter.ViewPagerAdapter
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.shared.view.SwipeDisabledViewPager.SwipeDisabledViewPager
import fr.iut.piscinenetptut.ui.managementCustomer.customer.CustomerFragment
import fr.iut.piscinenetptut.ui.managementCustomer.swimmingpool.SwimmingPoolFragment
import fr.iut.piscinenetptut.ui.home.HomeActivity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File


class ManagementCustomerActivityMvcImpl(
    private val context: Context,
    private val managementCustomerActivity: ManagementCustomerActivity
): ManagementCustomerActivityMvc {

    val TAG: String = "ManagementCustomerActivityMvc"

    var root: View? = null

    lateinit var listFragmentForViewPager: ArrayList<Fragment>

    init {
        try {
            root = View.inflate(context, R.layout.activity_add_customer, null)

            managementCustomerActivity.supportActionBar?.hide()

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

    override fun onCustomerInformationIsLoaded(customer: Customer) {
        try {
            if (null != root) {

                val json = Json(JsonConfiguration.Stable)
                val request = httpRequest()

                if (managementCustomerActivity.customer != null){
                    Fuel.put(request.url+"Customer/" + managementCustomerActivity.customer!!.ID)
                        .body(request.convertData(json.stringify(Customer.serializer(), customer)))
                        .header("Content-Type" to "application/x-www-form-urlencoded")
                        .responseString { request, response, result ->
                            result.fold({ d ->
                                Toast.makeText(listFragmentForViewPager[0].activity, "Updated !", Toast.LENGTH_LONG).show()
                            }, { err ->
                                println(err.message)
                            })
                        }
                }else {
                    Fuel.post(request.url+"Customer")
                        .body(request.convertData(json.stringify(Customer.serializer(), customer)))
                        .header("Content-Type" to "application/x-www-form-urlencoded")
                        .responseString { request, response, result ->
                            result.fold({ d ->
                                managementCustomerActivity.onUserWantToAddNewPool(json.parse(Customer.serializer(), d).ID)
                            }, { err ->
                                println(err.message)
                            })
                        }
                }

            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun onPoolInformationIsLoaded(pool: Pool){
        try {
            if (null != root) {

                val json = Json(JsonConfiguration.Stable)
                val file : String? = (listFragmentForViewPager[1] as SwimmingPoolFragment).uriPicture
                val request = httpRequest()

                if (file != null){
                    Fuel.upload(request.url + "Picture").add{ FileDataPart(File(file), name = "picture", filename=pool.picture) }
                        .response { result ->
                            println(result)
                        }
                }

                if (managementCustomerActivity.pool != null){
                    println(request.url+"Pool/" + pool.ID_Customer)
                    Fuel.put(request.url+"Pool/" + pool.ID_Customer)
                        .body(request.convertData(json.stringify(Pool.serializer(), pool)))
                        .header("Content-Type" to "application/x-www-form-urlencoded")
                        .responseString { request, response, result ->
                            result.fold({ d ->
                                Toast.makeText(listFragmentForViewPager[1].activity, "Updated !", Toast.LENGTH_LONG).show()
                            }, { err ->
                                println(err.message)
                            })
                        }
                } else {
                    Fuel.post(request.url+"Pool")
                        .body(request.convertData(json.stringify(Pool.serializer(), pool)))
                        .header("Content-Type" to "application/x-www-form-urlencoded")
                        .responseString { request, response, result ->
                            result.fold({ d ->
                                this@ManagementCustomerActivityMvcImpl.managementCustomerActivity.finish()
                                HomeActivity.start(this@ManagementCustomerActivityMvcImpl.managementCustomerActivity)
                            }, { err ->
                                println(err.message)
                            })
                        }
                }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}