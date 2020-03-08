package fr.iut.piscinenetptut.ui.visitDetails.maintenanceSheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MaintenanceSheetFragment : Fragment(), MaintenanceSheetFragmentMvc.Listener{

    lateinit var maintenanceSheetFragmentMvcImpl: MaintenanceSheetFragmentMvcImpl
    lateinit var maintenanceSheetFragmentViewModel: MaintenanceSheetFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        maintenanceSheetFragmentMvcImpl = MaintenanceSheetFragmentMvcImpl(inflater.context, this)
        maintenanceSheetFragmentViewModel = MaintenanceSheetFragmentViewModel()
        maintenanceSheetFragmentViewModel.showDetailMaintenance(maintenanceSheetFragmentMvcImpl.root!!)
        return maintenanceSheetFragmentMvcImpl.root
    }
}