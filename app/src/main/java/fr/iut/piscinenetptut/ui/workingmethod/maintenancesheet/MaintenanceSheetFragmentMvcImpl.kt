package fr.iut.piscinenetptut.ui.workingmethod.maintenancesheet

import android.content.Context
import android.view.View
import android.widget.Button
import fr.iut.piscinenetptut.R
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

//            root!!.findViewById<Button>(R.id.workingButtonMaintenance)?.setOnClickListener{
//                (maintenanceSheetFragment.activity as WorkingMethodActivity).onUserWantToStoreMaintenance()
//                (maintenanceSheetFragment.activity as WorkingMethodActivity).workingMethodActivityMcvImpl.showTechnicalSheet()
//            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}