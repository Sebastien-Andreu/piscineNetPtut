package fr.iut.piscinenetptut.ui.listOfCustomer

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.view.size
import com.github.kittinunf.fuel.Fuel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.*
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.ui.listOfCustomer.item.UserPreviewFactory
import fr.iut.piscinenetptut.ui.managementCustomer.ManagementCustomerActivity

class ListCustomerActivityMvcImpl(
    val context: Context,
    val listUserActivity: ListCustomerActivity
): ListCustomerActivityMvc {

    private val TAG: String = "ListCustomerActivityMvcImpl"

    private val requestHttp = httpRequest()

    private val userPreviewClickListener: View.OnClickListener = View.OnClickListener { v ->
        if (null != v) {
            listUserActivity.onUserTouchUserPreview(v.tag.toString().toInt())
        }
    }

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_user_list, null)
            listUserActivity.activity?.findViewById<TextView>(R.id.textToolBar)?.text = context.getString(R.string.ListCustomer)


            if (root != null){

                if( Account.register.role == "employee" ){
                    root!!.findViewById<FloatingActionButton>(R.id.floatingActionButtonAddCustomer)?.hide()
                }

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
            if (null != root && root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.size == 0) {
                for ((i, customer) in customers.withIndex()) {
                    val view = UserPreviewFactory.createUserPreviewForUser(
                        userPreviewClickListener = userPreviewClickListener, customer = customer, picture = pools[i].picture!!, context = context)

                    root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.addView(view)
                }
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun verifyIfUpdateDataBase() {
        try {
            Fuel.get(requestHttp.url+"ThereIsAnUpdateForCustomer")
                .responseString { _, _, result ->
                    result.fold({ d ->

                        if( Version.versionCustomer < d.toInt()) {
                            Version.versionCustomer = d.toInt()
                            root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.removeAllViews()
                            listUserActivity.listUserActivityViewModel.onNeedToGetUserList()
                        } else if ( root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.size == 0){
                            listUserActivity.listUserActivityViewModel.onNeedToGetUserList()
                        }

                    }, { err ->
                        println(err.message)
                    })
                }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}