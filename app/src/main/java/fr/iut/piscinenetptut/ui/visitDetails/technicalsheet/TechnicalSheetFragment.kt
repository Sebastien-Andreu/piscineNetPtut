package fr.iut.piscinenetptut.ui.visitDetails.technicalsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TechnicalSheetFragment : Fragment(), TechnicalSheetFragmentMvc.Listener{

    lateinit var technicalSheetFragmentMvcImpl: TechnicalSheetFragmentMvcImpl
    lateinit var technicalSheetFragmentViewModel: TechnicalSheetFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        technicalSheetFragmentMvcImpl = TechnicalSheetFragmentMvcImpl(inflater.context, this)
        technicalSheetFragmentViewModel = TechnicalSheetFragmentViewModel()
        technicalSheetFragmentViewModel.showDetailTechnical(technicalSheetFragmentMvcImpl.root!!)
        return technicalSheetFragmentMvcImpl.root
    }
}