package fr.iut.piscinenetptut.ui.listOfUser.item

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.User
import fr.iut.piscinenetptut.library.extension.toTreatFor

class UserPreviewFactory {
    companion object {
        val TAG: String = "UserPreviewFactory"

        fun createUserPreviewForUser(user: User, userPreviewClickListener: View.OnClickListener, context: Context): View {
            try {
                val view: View = View.inflate(context, R.layout.user_list_user_preview, null)

                view.tag = user.userId
                view.setOnClickListener(userPreviewClickListener)


                view.findViewById<TextView>(R.id.firstNameTextView)?.text = user.firstName
                view.findViewById<TextView>(R.id.surNameTextView)?.text = user.surName

                if (user.isWarnings) {
                    view.findViewById<RelativeLayout>(R.id.warningWrapper)?.visibility =
                        View.VISIBLE
                }

                return view
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }

            return View(context)
        }
    }
}