package fr.iut.piscinenetptut.ui.employeeDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.managementEmployee.ManagementEmployeeActivity

class EmployeeDetailsActivity: AppCompatActivity(), EmployeeDetailsMvc.Listeners {

    companion object {
        private val TAG:String = "EmployeeDetailsActivity"

        fun start(context: Context) {
            try {

                context.startActivity(Intent(context, EmployeeDetailsActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    private lateinit var employeeDetailsMvcImpl: EmployeeDetailsMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = "Employee details"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            employeeDetailsMvcImpl = EmployeeDetailsMvcImpl(this, this)
            setContentView(employeeDetailsMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    /**
    *   Called when an employee need to be updated
    **/
    override fun onUserWantToUpdateEmployee() {
        try {
            ManagementEmployeeActivity.start(this)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }


    /**
    *   Called when the user press the back button
    **/
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
    *   Close the view
    **/
    override fun onBackPressed() {
        this@EmployeeDetailsActivity.finish()
    }

    
    override fun onResume() {
        super.onResume()
        employeeDetailsMvcImpl.onUserWantToShowDetailEmployee()
    }
}