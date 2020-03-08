package fr.iut.piscinenetptut.ui.visitDetails.technicalsheet

import android.view.View
import android.widget.EditText
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Technical
import fr.iut.piscinenetptut.entities.VisitSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import java.lang.Exception

class TechnicalSheetFragmentViewModel {

    private val TAG: String = "TechnicalSheetFragmentViewModel"


    fun showDetailTechnical(root: View){
        try {

            val technical = VisitSelected.technical
            disableAll(root)

            if (technical.Chlore != null){
                root.findViewById<EditText>(R.id.AnalyseChlore)?.setText(technical.Chlore.toString())
            }
            if (technical.PH != null){
                root.findViewById<EditText>(R.id.AnalysePh)?.setText(technical.PH.toString())
            }
            if (technical.stabilisant != null){
                root.findViewById<EditText>(R.id.AnalyseStab)?.setText(technical.stabilisant.toString())
            }
            if (technical.TAC != null){
                root.findViewById<EditText>(R.id.AnalyseTAC)?.setText(technical.TAC.toString())
            }
            if (technical.Sel != null){
                root.findViewById<EditText>(R.id.AnalyseSel)?.setText(technical.Sel.toString())
            }
            if (technical.ProductChlore != null){
                root.findViewById<EditText>(R.id.ProductChlore)?.setText(technical.ProductChlore.toString())
            }
            if (technical.ProductPH != null){
                root.findViewById<EditText>(R.id.ProductPH)?.setText(technical.ProductPH.toString())
            }
            if (technical.ProductGalet != null){
                root.findViewById<EditText>(R.id.ProductGalet)?.setText(technical.ProductGalet.toString())
            }
            if (technical.productSel != null){
                root.findViewById<EditText>(R.id.ProductSel)?.setText(technical.productSel.toString())
            }
            if (technical.ProductFloculent != null){
                root.findViewById<EditText>(R.id.ProductFloc)?.setText(technical.ProductFloculent.toString())
            }
            if (technical.ProductOther != null){
                root.findViewById<EditText>(R.id.ProductOther)?.setText(technical.ProductOther.toString())
            }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    private fun disableAll(root: View) {
        root.findViewById<EditText>(R.id.AnalyseChlore)?.inputType = 0
        root.findViewById<EditText>(R.id.AnalysePh)?.inputType = 0
        root.findViewById<EditText>(R.id.AnalyseStab)?.inputType = 0
        root.findViewById<EditText>(R.id.AnalyseTAC)?.inputType = 0
        root.findViewById<EditText>(R.id.AnalyseSel)?.inputType = 0
        root.findViewById<EditText>(R.id.ProductChlore)?.inputType = 0
        root.findViewById<EditText>(R.id.ProductPH)?.inputType = 0
        root.findViewById<EditText>(R.id.ProductGalet)?.inputType = 0
        root.findViewById<EditText>(R.id.ProductSel)?.inputType = 0
        root.findViewById<EditText>(R.id.ProductFloc)?.inputType = 0
        root.findViewById<EditText>(R.id.ProductOther)?.inputType = 0
    }
}