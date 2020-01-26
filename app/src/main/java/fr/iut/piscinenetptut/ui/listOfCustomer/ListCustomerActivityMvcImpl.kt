package fr.iut.piscinenetptut.ui.listOfCustomer

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.ui.listOfCustomer.item.UserPreviewFactory
import fr.iut.piscinenetptut.ui.managementCustomer.ManagementCustomerActivity

class ListCustomerActivityMvcImpl(
    val context: Context,
    val listUserActivity: ListCustomerActivity
): ListCustomerActivityMvc {

    private val TAG: String = "ListUserActivityMvcImpl"

    private val requestHttp = httpRequest()

    private val userPreviewClickListener: View.OnClickListener = View.OnClickListener { v ->
        if (null != v) {
            listUserActivity.onUserTouchUserPreview(v.tag.toString().toInt())
        }
    }

    var root: View? = null
    var listIsLoad: Boolean = false

    init {
        try {
            root = View.inflate(context, R.layout.activity_user_list, null)
            listUserActivity.activity?.findViewById<TextView>(R.id.textToolBar)?.text = "List of customer"

            if (root != null){
                root!!.findViewById<FloatingActionButton>(R.id.floatingActionButtonAddCustomer)?.setOnClickListener {
                    CustomerSelected.reset()
                    ManagementCustomerActivity.start(listUserActivity.layoutInflater.context)
                }
            }

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserListLoaded(customers: List<Customer>, pools: List<Pool>) {
        try {
            if (null != root) {
                for ((i, customer) in customers.withIndex()) {
                    val view = UserPreviewFactory.createUserPreviewForUser(
                        userPreviewClickListener = userPreviewClickListener, customer = customer, picture = pools[i].picture!!, context = context)

                    root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.addView(view)
                }
                listIsLoad = true
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun verifyIfUpdateDataBase() {
        try {
            if (listIsLoad){
                Fuel.get(requestHttp.url+"ThereIsAnUpdate")
                    .responseString { _, _, result ->
                        result.fold({ d ->
                            if (d == true.toString()){
                                root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.removeAllViews()
                                listUserActivity.listUserActivityViewModel.onNeedToGetUserList()
                            }
                        }, { err ->
                            println(err.message)
                        })
                    }
            } else {
                listUserActivity.listUserActivityViewModel.onNeedToGetUserList()
            }


        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}