package fr.iut.piscinenetptut.ui.workingmethod.maintenancesheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fr.iut.piscinenetptut.R

class MaintenanceSheetFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maintenance_sheet, container, false)
    }
}