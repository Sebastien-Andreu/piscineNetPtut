package fr.iut.piscinenetptut.ui.listOfVisit

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Visit
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.listOfVisit.item.VisitPreviewFactory

class ListOfVisitActivityMvcImpl(
    val context: Context,
    val listOfVisitActivity: ListOfVisitActivity
): ListOfVisitActivityMvc {

    val TAG: String = "ListUserActivityMvcImpl"

    private val visitPreviewClickListener: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            if (null != v) {
                listOfVisitActivity.onUserTouchVisitPreview(v.tag.toString())
            }
        }
    }

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_visit_list, null)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onVisitListLoaded(visits: ArrayList<Visit>) {
        try {
            if (null != root) {
                for (visit in visits) {
                    val view = VisitPreviewFactory.createVisitPreviewForUser(visitPreviewClickListener = visitPreviewClickListener, visit = visit, context = context)

                    root!!.findViewById<LinearLayout>(R.id.listVisitWrapper)?.addView(view)
                }
            }
        } catch (exception: Exception) {
                exception.toTreatFor(TAG)
        }
    }
}