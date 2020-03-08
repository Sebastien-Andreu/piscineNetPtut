package fr.iut.piscinenetptut.ui.workingmethod.observation

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity

class ObservationFragmentMvcImpl (
    private val context: Context,
    private val observationFragment: ObservationFragment
): ObservationFragmentMvc {

    private val TAG = "ObservationFragmentMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.fragment_observation, null)
            root!!.findViewById<TextView>(R.id.nameCustomerObservation).text = (CustomerSelected.customer.surname + " " + CustomerSelected.customer.name)


            root!!.findViewById<Button>(R.id.workingButtonFinalize)?.setOnClickListener{
                (observationFragment.activity as WorkingMethodActivity).onUserWantToFinishVisit()
            }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}