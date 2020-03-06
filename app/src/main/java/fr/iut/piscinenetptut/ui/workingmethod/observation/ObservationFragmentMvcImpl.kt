package fr.iut.piscinenetptut.ui.workingmethod.observation

import android.content.Context
import android.view.View
import android.widget.Button
import fr.iut.piscinenetptut.R
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

            root!!.findViewById<Button>(R.id.workingButtonFinalize)?.setOnClickListener{
                (observationFragment.activity as WorkingMethodActivity).onUserWantToStoreVisit()
            }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}