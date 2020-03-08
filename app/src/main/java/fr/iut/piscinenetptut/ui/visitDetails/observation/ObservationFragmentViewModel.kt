package fr.iut.piscinenetptut.ui.visitDetails.observation

import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.forEach
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Observation
import fr.iut.piscinenetptut.entities.Visit
import fr.iut.piscinenetptut.entities.VisitSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import java.lang.Exception

class ObservationFragmentViewModel {
    private val TAG: String = "ObservationFragmentViewModel"


    fun showDetailObservation(root: View){
        try {
            val observation = VisitSelected.observation
            disableAll(root)

            root.findViewById<RadioGroup>(R.id.ObservationVisual)?.forEach {
                if ((it as RadioButton).text == observation.visualObservation){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }
            root.findViewById<RadioGroup>(R.id.ObservationWalls)?.forEach {
                if ((it as RadioButton).text == observation.algeaOnWall){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }
            root.findViewById<RadioGroup>(R.id.ObservationBrossage)?.forEach {
                if ((it as RadioButton).text == observation.brossage){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }

            if (observation.otherObservation != null){
                root.findViewById<EditText>(R.id.ObservationProblem)?.setText(observation.otherObservation.toString())
            }
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    private fun disableAll(root: View) {
        root.findViewById<RadioGroup>(R.id.ObservationVisual)?.forEach {
            root.findViewById<RadioButton>(it.id).isClickable = false
        }
        root.findViewById<RadioGroup>(R.id.ObservationWalls)?.forEach {
            root.findViewById<RadioButton>(it.id).isClickable = false
        }
        root.findViewById<RadioGroup>(R.id.ObservationBrossage)?.forEach {
            root.findViewById<RadioButton>(it.id).isClickable = false
        }

        root.findViewById<EditText>(R.id.ObservationProblem)?.inputType = 0
    }

}