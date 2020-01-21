package fr.iut.piscinenetptut.ui.customerdetails.swimmingpool

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SwimmingPoolDetailFragment : Fragment(){

    private lateinit var swimmingPoolDetailFragmentMvcImpl: SwimmingPoolDetailFragmentMvcImpl


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        swimmingPoolDetailFragmentMvcImpl = SwimmingPoolDetailFragmentMvcImpl(inflater.context, this)
        return swimmingPoolDetailFragmentMvcImpl.root
    }

    override fun onResume() {
        super.onResume()
        swimmingPoolDetailFragmentMvcImpl.onUserWantToShowDetailPool()
    }
}
