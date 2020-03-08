package fr.iut.piscinenetptut.ui.workingmethod.technicalsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity

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

        if ((this.activity as WorkingMethodActivity).workingMethodActivityMcvImpl.technical != null){
            technicalSheetFragmentViewModel.showDetailTechnical(technicalSheetFragmentMvcImpl.root!!, (this.activity as WorkingMethodActivity).workingMethodActivityMcvImpl.technical!!)
        }
        return technicalSheetFragmentMvcImpl.root
    }
}