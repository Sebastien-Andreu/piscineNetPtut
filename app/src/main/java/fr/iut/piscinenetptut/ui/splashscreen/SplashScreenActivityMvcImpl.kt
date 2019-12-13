package fr.iut.piscinenetptut.ui.splashscreen

import android.content.Context
import android.view.View
import android.widget.Button
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor

class SplashScreenActivityMvcImpl (
    splashScreenActivity: SplashScreenActivity,
    context: Context
): SplashScreenActivityMvc {

    val TAG: String = "SplashScreenActivityMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_splashscreen, null)

            if (null != root) {
                root!!.findViewById<Button>(R.id.buttonLogin)?.setOnClickListener {
                    splashScreenActivity.onUserWantToLogin()
                }
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}