package fr.iut.piscinenetptut.ui.splashscreen

import android.content.Context
import android.view.View
import android.widget.Button
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor
import java.net.URL


class SplashScreenActivityMvcImpl (
    splashScreenActivity: SplashScreenActivity,
    context: Context
): SplashScreenActivityMvc {

    val TAG: String = "SplashScreenActivityMvcImpl"

    var root: View? = null

    init {
        try {
//            val thread = Thread(Runnable {
//                try {
//                    println(URL("https://good-goose-90.tunnel.datahub.at/auth").readText())
//                } catch (e: java.lang.Exception) {
//                    e.printStackTrace()
//                }
//            })
//            thread.start()

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