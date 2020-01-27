package fr.iut.piscinenetptut.ui.managementEmployee

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.entities.EmployeeSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor

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

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = "Add new employee"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)


            if (EmployeeSelected.employee.ID != null){
                supportActionBar?.title = "Update information"
            }

            managementEmployeeViewModel = ManagementEmployeeViewModel()
            managementEmployeeMvcImpl = ManagementEmployeeMvcImpl(this, this)

            managementEmployeeViewModel.addEmployeeCallBack.observe(this, Observer {
                managementEmployeeMvcImpl.addEmployee(it)
            })

            managementEmployeeViewModel.updateEmployeeCallBack.observe(this, Observer {
                managementEmployeeMvcImpl.updateEmployee()
            })
            setContentView(managementEmployeeMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
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
        managementEmployeeViewModel.onNeedToGetEmployeeInformation(managementEmployeeMvcImpl.root!!)
    }
}