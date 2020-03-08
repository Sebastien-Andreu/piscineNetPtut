package fr.iut.piscinenetptut.ui.listOfVisit.item

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Visit
import fr.iut.piscinenetptut.library.extension.toTreatFor

class VisitPreviewFactory {

    companion object {
        val TAG: String = "VisitPreviewFactory"

        fun createVisitPreviewForUser(visit: Visit, visitPreviewClickListener: View.OnClickListener, context: Context): View {
            try {
                val view: View = View.inflate(context, R.layout.visit_list_preview, null)

                view.tag = visit.ID
                view.setOnClickListener(visitPreviewClickListener)


                view.findViewById<TextView>(R.id.visitNameCustomer)?.text = visit.NameCustomer
                view.findViewById<TextView>(R.id.visitListTextEmployee)?.text = visit.NameEmployee

                return view
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }

            return View(context)
        }
    }
}