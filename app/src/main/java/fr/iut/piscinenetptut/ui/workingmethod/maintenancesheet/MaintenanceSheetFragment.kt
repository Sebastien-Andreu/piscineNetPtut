package fr.iut.piscinenetptut.ui.workingmethod.maintenancesheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fr.iut.piscinenetptut.R

class MaintenanceSheetFragment : Fragment(), MaintenanceSheetFragmentMvc.Listener{

    lateinit var maintenanceSheetFragmentMvcImpl: MaintenanceSheetFragmentMvcImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        maintenanceSheetFragmentMvcImpl = MaintenanceSheetFragmentMvcImpl(inflater.context, this)
        return maintenanceSheetFragmentMvcImpl.root
    }
}