package fr.iut.piscinenetptut.ui.workingmethod.technicalsheet

import android.content.Context
import android.view.View
import android.widget.Button
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity

class TechnicalSheetFragmentMvcImpl  (
    private val context: Context,
    private val technicalSheetFragment: TechnicalSheetFragment
): TechnicalSheetFragmentMvc {

    private val TAG = "TechnicalSheetFragmentMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.fragment_technical_sheet, null)

//            root!!.findViewById<Button>(R.id.workingButtonTechnical)?.setOnClickListener{
//                (technicalSheetFragment.activity as WorkingMethodActivity).onUserWantToStoreTechnical()
//                (technicalSheetFragment.activity as WorkingMethodActivity).workingMethodActivityMcvImpl.showObservationSheet()
//            }
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}