package fr.iut.piscinenetptut.ui.addCustomer.swimmingpool

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer


class SwimmingPoolFragment : Fragment() {

    lateinit var swimmingPoolFragmentMvcImpl: SwimmingPoolFragmentMvcImpl
    var uriPicture: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        swimmingPoolFragmentMvcImpl = SwimmingPoolFragmentMvcImpl(inflater.context, this)

        swimmingPoolFragmentMvcImpl.filePicture.observe(this, Observer {
            uriPicture = it.toString()
        })

        return swimmingPoolFragmentMvcImpl.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1463) {
            if (data != null){
                swimmingPoolFragmentMvcImpl.showPicture(data)
            }
        }
    }
}
