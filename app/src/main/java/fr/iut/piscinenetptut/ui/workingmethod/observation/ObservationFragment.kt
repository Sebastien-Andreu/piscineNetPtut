package fr.iut.piscinenetptut.ui.workingmethod.observation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import fr.iut.piscinenetptut.R

class ObservationFragment : Fragment(), ObservationFragmentMvc.Listener{

    lateinit var observationFragmentMvcImpl: ObservationFragmentMvcImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observationFragmentMvcImpl = ObservationFragmentMvcImpl(inflater.context, this)
        return observationFragmentMvcImpl.root
    }
}