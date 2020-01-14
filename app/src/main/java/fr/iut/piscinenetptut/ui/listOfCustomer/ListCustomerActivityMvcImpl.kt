package fr.iut.piscinenetptut.ui.listOfCustomer

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.ui.listOfCustomer.item.UserPreviewFactory

class ListCustomerActivityMvcImpl(
    val context: Context,
    val listUserActivity: ListCustomerActivity
): ListCustomerActivityMvc {

    private val TAG: String = "ListUserActivityMvcImpl"

    private val userPreviewClickListener: View.OnClickListener = View.OnClickListener { v ->
        if (null != v) {
            listUserActivity.onUserTouchUserPreview(v.tag.toString().toInt())
        }
    }

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_user_list, null)
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
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun getPictureOfCustomer(idCustomer: Int){
        val requestHttp = httpRequest()
        Fuel.get(requestHttp.url + "Pool")
    }
}