package fr.iut.piscinenetptut.ui.workingmethod.maintenancesheet

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity

class MaintenanceSheetFragmentMvcImpl (
    private val context: Context,
    private val maintenanceSheetFragment: MaintenanceSheetFragment
): MaintenanceSheetFragmentMvc {

    private val TAG = "MaintenanceSheetFragmentMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.fragment_maintenance_sheet, null)
            root!!.findViewById<TextView>(R.id.workingNameCustomer).text = (CustomerSelected.customer.surname + " " + CustomerSelected.customer.name)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}