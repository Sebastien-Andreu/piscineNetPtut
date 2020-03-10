package fr.iut.piscinenetptut.ui.managementEmployee

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.EmployeeSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.permission.PermissionCamera

class ManagementEmployeeActivity: AppCompatActivity(), ManagementEmployeeMvc.Listeners {

    val TAG: String = "ManagementEmployeeActivity"

    companion object {
        private val TAG: String = "ManagementEmployeeActivity"

        fun start(context: Context) {
            try {
                context.startActivity(Intent(context, ManagementEmployeeActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var managementEmployeeMvcImpl: ManagementEmployeeMvcImpl
    lateinit var managementEmployeeViewModel: ManagementEmployeeViewModel

    lateinit var permissionCamera: PermissionCamera
    var uriPicture: String? = null
    var permissionResult : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = getString(R.string.AddEmployee)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            permissionCamera = PermissionCamera()


            if (EmployeeSelected.employee.ID != null){
                supportActionBar?.title = getString(R.string.UpdateEmployee)
            }

            managementEmployeeViewModel = ManagementEmployeeViewModel()
            managementEmployeeMvcImpl = ManagementEmployeeMvcImpl(this, this)

            managementEmployeeViewModel.addEmployeeCallBack.observe(this, Observer {
                managementEmployeeMvcImpl.addEmployee(it)
            })

            managementEmployeeViewModel.updateEmployeeCallBack.observe(this, Observer {
                managementEmployeeMvcImpl.updateEmployee()
            })

            managementEmployeeMvcImpl.filePicture.observe(this, Observer {
                uriPicture = it.toString()
            })

            setContentView(managementEmployeeMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1463) {
            if (data != null){
                managementEmployeeMvcImpl.showPicture(data)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    permissionCamera.setShouldShowStatus(this, Manifest.permission.CAMERA)
                    permissionResult = false
                } else {
                    permissionResult = true
                }
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        this@ManagementEmployeeActivity.finish()
    }

    override fun onUserWantToAddEmployee() {
        managementEmployeeViewModel.onNeedToGetEmployeeInformation(managementEmployeeMvcImpl.root!!, null)
    }

    override fun onUserWantToModifyEmployeeInformation(){
        managementEmployeeViewModel.onNeedToGetEmployeeInformation(managementEmployeeMvcImpl.root!!, picture = EmployeeSelected.employee.picture)
    }
}