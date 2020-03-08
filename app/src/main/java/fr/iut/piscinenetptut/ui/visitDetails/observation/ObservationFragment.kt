package fr.iut.piscinenetptut.ui.visitDetails.observation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

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
        observationFragmentViewModel.showDetailObservation(observationFragmentMvcImpl.root!!)
        return observationFragmentMvcImpl.root
    }
}