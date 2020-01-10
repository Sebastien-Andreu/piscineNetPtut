package fr.iut.piscinenetptut.ui.addCustomer.swimmingpool

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.shared.permission.PermissionCamera


class SwimmingPoolFragment : Fragment() {

    private lateinit var swimmingPoolFragmentMvcImpl: SwimmingPoolFragmentMvcImpl
    lateinit var permissionCamera: PermissionCamera

    var uriPicture: String? = null
    var permissionResult : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        swimmingPoolFragmentMvcImpl = SwimmingPoolFragmentMvcImpl(inflater.context, this)
        permissionCamera = PermissionCamera()

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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    permissionCamera.setShouldShowStatus(this.context!!, Manifest.permission.CAMERA)
                    permissionResult = false
                } else {
                    permissionResult = true
                }
            }
        }
    }

}
