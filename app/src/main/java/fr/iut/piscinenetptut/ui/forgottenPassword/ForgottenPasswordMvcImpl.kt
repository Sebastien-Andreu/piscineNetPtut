package fr.iut.piscinenetptut.ui.forgottenPassword

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor

class ForgottenPasswordMvcImpl (
    private val context: Context,
    private val forgottenPasswordActivity: ForgottenPasswordActivity
): ForgottenPasswordMvc {
    val TAG: String = "ForgottenPasswordMvcImpl"
    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_forgotten_password, null);
            if (null != root) {
                root?.findViewById<Button>(R.id.forgottenPaswwordSendMailButton)?.setOnClickListener {
                    forgottenPasswordActivity.onUserWantToSendNewPassword()
                }
            }

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}