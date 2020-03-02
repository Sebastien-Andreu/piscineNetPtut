package fr.iut.piscinenetptut.ui.workingmethod.technicalsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.iut.piscinenetptut.R

class TechnicalSheetFragment : Fragment(), TechnicalSheetFragmentMvc.Listener{

    lateinit var technicalSheetFragmentMvcImpl: TechnicalSheetFragmentMvcImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        technicalSheetFragmentMvcImpl = TechnicalSheetFragmentMvcImpl(inflater.context, this)
        return technicalSheetFragmentMvcImpl.root
    }
}