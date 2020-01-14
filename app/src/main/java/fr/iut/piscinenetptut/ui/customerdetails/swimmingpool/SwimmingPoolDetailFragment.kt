package fr.iut.piscinenetptut.ui.customerdetails.swimmingpool

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fr.iut.piscinenetptut.R

class SwimmingPoolDetailFragment : Fragment(), SwimmingPoolDetailFragmentMvc.Listener {

    lateinit var swimmingPoolDetailFragmentMvcImpl: SwimmingPoolDetailFragmentMvcImpl


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        swimmingPoolDetailFragmentMvcImpl = SwimmingPoolDetailFragmentMvcImpl(inflater.context, this)
        return swimmingPoolDetailFragmentMvcImpl.root
    }
}
