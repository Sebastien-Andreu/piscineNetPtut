package fr.iut.piscinenetptut.ui.welcomeCustomer

import android.content.Context
import android.view.View
import android.widget.TextView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor

class WelcomeCustomerMvcImpl(
    val context: Context,
    val welcomeCustomerActivity: WelcomeCustomerActivity
): WelcomeCustomerMvc {

    private val TAG: String = "WelcomeCustomerMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_welcome_customer, null)
            welcomeCustomerActivity.activity?.findViewById<TextView>(R.id.textToolBar)?.text = welcomeCustomerActivity.context?.getString(R.string.app_name)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}