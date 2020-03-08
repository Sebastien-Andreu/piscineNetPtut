package fr.iut.piscinenetptut.ui.workingmethod.observation

import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.forEach
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Observation
import fr.iut.piscinenetptut.library.extension.toTreatFor
import java.lang.Exception

class ObservationFragmentViewModel {
    private val TAG: String = "ObservationFragmentViewModel"


    fun showDetailObservation(root: View, observation: Observation){
        try {
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
}