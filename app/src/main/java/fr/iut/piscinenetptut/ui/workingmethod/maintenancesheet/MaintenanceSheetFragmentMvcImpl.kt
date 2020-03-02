package fr.iut.piscinenetptut.ui.workingmethod.maintenancesheet

import android.content.Context
import android.view.View
import fr.iut.piscinenetptut.R
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

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}