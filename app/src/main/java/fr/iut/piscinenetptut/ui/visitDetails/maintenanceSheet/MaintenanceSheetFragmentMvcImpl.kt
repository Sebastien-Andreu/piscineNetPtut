package fr.iut.piscinenetptut.ui.visitDetails.maintenanceSheet

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.VisitSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor


class MaintenanceSheetFragmentMvcImpl (
    private val context: Context,
    private val maintenanceSheetFragment: MaintenanceSheetFragment
): MaintenanceSheetFragmentMvc {

    private val TAG = "MaintenanceSheetFragmentMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.fragment_maintenance_sheet, null)
            root!!.findViewById<TextView>(R.id.workingNameCustomer).text = (VisitSelected.visit.NameCustomer)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}