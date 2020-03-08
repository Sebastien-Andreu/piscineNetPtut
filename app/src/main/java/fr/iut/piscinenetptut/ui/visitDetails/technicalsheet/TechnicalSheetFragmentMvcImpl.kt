package fr.iut.piscinenetptut.ui.visitDetails.technicalsheet

import android.content.Context
import android.view.View
import android.widget.TextView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.VisitSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor

class TechnicalSheetFragmentMvcImpl  (
    private val context: Context,
    private val technicalSheetFragment: TechnicalSheetFragment
): TechnicalSheetFragmentMvc {

    private val TAG = "TechnicalSheetFragmentMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.fragment_technical_sheet, null)
            root!!.findViewById<TextView>(R.id.nameCustomerTechnical).text = (VisitSelected.visit.NameCustomer)

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}