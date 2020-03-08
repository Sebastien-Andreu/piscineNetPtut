package fr.iut.piscinenetptut.ui.workingmethod.observation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity

class ObservationFragment : Fragment(), ObservationFragmentMvc.Listener{

    lateinit var observationFragmentMvcImpl: ObservationFragmentMvcImpl
    lateinit var observationFragmentViewModel: ObservationFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observationFragmentMvcImpl = ObservationFragmentMvcImpl(inflater.context, this)
        observationFragmentViewModel = ObservationFragmentViewModel()

        if ((this.activity as WorkingMethodActivity).workingMethodActivityMcvImpl.observation != null){
            observationFragmentViewModel.showDetailObservation(observationFragmentMvcImpl.root!!, (this.activity as WorkingMethodActivity).workingMethodActivityMcvImpl.observation!!)
        }

        return observationFragmentMvcImpl.root
    }
}