package fr.iut.piscinenetptut.ui.workingmethod.maintenancesheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity

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

        if ((this.activity as WorkingMethodActivity).workingMethodActivityMcvImpl.maintenance != null){
            maintenanceSheetFragmentViewModel.showDetailMaintenance(maintenanceSheetFragmentMvcImpl.root!!, (this.activity as WorkingMethodActivity).workingMethodActivityMcvImpl.maintenance!!)
        }
        return maintenanceSheetFragmentMvcImpl.root
    }
}