package fr.iut.piscinenetptut.ui.home

import android.content.Context
import android.view.View
import android.widget.Button
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor

class HomeActivtyMvcImpl(
    val homeActivity: HomeActivity,
    val context: Context
): HomeActivtyMvc {

    val TAG: String = "HomeActivtyMvcImpl"
    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.layout_home, null)

            if (null != root) {
                root!!.findViewById<Button>(R.id.addUserHomeButton)?.setOnClickListener {
                    homeActivity.onUserWantToAddAClient()
                }

                root!!.findViewById<Button>(R.id.showAllUserHomeButton)?.setOnClickListener {
                    homeActivity.onUserWantToSeeAllClient()
                }

                root!!.findViewById<Button>(R.id.showAllVisitButton)?.setOnClickListener {
                    homeActivity.onUserWanttoSeeAllVisit()
                }

            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}