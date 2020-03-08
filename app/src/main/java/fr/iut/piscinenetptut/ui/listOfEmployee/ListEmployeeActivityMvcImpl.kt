package fr.iut.piscinenetptut.ui.listOfEmployee

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import com.github.kittinunf.fuel.Fuel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Employee
import fr.iut.piscinenetptut.entities.EmployeeSelected
import fr.iut.piscinenetptut.entities.Version
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.ui.listOfEmployee.item.EmployeePreviewFactory
import fr.iut.piscinenetptut.ui.managementEmployee.ManagementEmployeeActivity

class ListEmployeeActivityMvcImpl(
    val context: Context,
    val listEmployeeActivity: ListEmployeeActivity
): ListEmployeeActivityMvc {

    private val TAG: String = "ListUserActivityMvcImpl"

    private val requestHttp = httpRequest()

    private val userPreviewClickListener: View.OnClickListener = View.OnClickListener { v ->
        if (null != v) {
            listEmployeeActivity.onUserTouchUserPreview(v.tag.toString().toInt())
        }
    }

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_user_list, null)
            listEmployeeActivity.activity?.findViewById<TextView>(R.id.textToolBar)?.text = "List of employee"

            if (root != null){
                root!!.findViewById<FloatingActionButton>(R.id.floatingActionButtonAddCustomer)?.setOnClickListener {
                    EmployeeSelected.reset()
                    ManagementEmployeeActivity.start(listEmployeeActivity.layoutInflater.context)
                }
            }

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onEmployeeListLoaded(employees: List<Employee>) {
        try {
            if (null != root && root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.size == 0) {
                employees.forEach {
                    val view = EmployeePreviewFactory.createEmployeePreviewForUser(
                        userPreviewClickListener = userPreviewClickListener, employee = it, context = context)

                    root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.addView(view)
                }
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun verifyIfUpdateDataBase() {
        try {
            Fuel.get(requestHttp.url+"ThereIsAnUpdateForEmployee")
                .responseString { _, _, result ->
                    result.fold({ d ->
                        if( Version.versionEmployee < d.toInt()){
                            Version.versionEmployee = d.toInt()
                            root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.removeAllViews()
                            listEmployeeActivity.listEmployeeActivityViewModel.onNeedToGetEmployeeList()
                        } else if ( root!!.findViewById<LinearLayout>(R.id.listUserWrapper)?.size == 0){
                            listEmployeeActivity.listEmployeeActivityViewModel.onNeedToGetEmployeeList()
                        }
                    }, { err ->
                        println(err.message)
                    })
                }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}