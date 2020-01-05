package fr.iut.piscinenetptut.ui.addCustomer.swimmingpool

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class SwimmingPoolFragment : Fragment() {

    lateinit var swimmingPoolFragmentMvcImpl: SwimmingPoolFragmentMvcImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        swimmingPoolFragmentMvcImpl = SwimmingPoolFragmentMvcImpl(inflater.context, this)
        return swimmingPoolFragmentMvcImpl.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1463) {
            swimmingPoolFragmentMvcImpl.showPicture(data)
        }
    }
}
