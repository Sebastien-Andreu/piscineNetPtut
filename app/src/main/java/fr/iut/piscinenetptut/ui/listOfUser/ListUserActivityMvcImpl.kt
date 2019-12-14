package fr.iut.piscinenetptut.ui.listOfUser

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.User
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.listOfUser.item.UserPreviewFactory

class ListUserActivityMvcImpl(
    val context: Context,
    val listUserActivity: ListUserActivity
): ListUserActivityMvc {

    val TAG: String = "ListUserActivityMvcImpl"

    val userPreviewClickListener: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            if (null != v) {
                listUserActivity.onUserTouchUserPreview(v.tag.toString())
            }
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

    override fun onUserListLoaded(users: ArrayList<User>) {
        try {
            if (null != root) {
                for (user in users) {
                    val view = UserPreviewFactory.createUserPreviewForUser(userPreviewClickListener = userPreviewClickListener, user = user, context = context)

                    root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.addView(view)
                }
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}