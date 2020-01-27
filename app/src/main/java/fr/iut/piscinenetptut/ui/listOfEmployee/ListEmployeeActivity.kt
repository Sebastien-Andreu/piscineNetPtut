package fr.iut.piscinenetptut.ui.listOfEmployee

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.entities.*
import fr.iut.piscinenetptut.library.extension.toTreatFor


class ListEmployeeActivity: Fragment(), ListEmployeeActivityMvc.Listeners {

    val TAG: String = "ListUserActivity"

    lateinit var listEmployeeActivityMvcImpl: ListEmployeeActivityMvcImpl
    var listEmployeeActivityViewModel = ListEmployeeActivityViewModel()

    lateinit var listEmployee: List<Employee>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listEmployeeActivityMvcImpl = ListEmployeeActivityMvcImpl(inflater.context, this)
        listEmployeeActivityViewModel = ListEmployeeActivityViewModel()

        listEmployeeActivityMvcImpl.listIsLoad = false

        listEmployeeActivityViewModel.employeeCallBack.observe(this, Observer {
            listEmployee = it
            listEmployeeActivityMvcImpl.onEmployeeListLoaded(it)
        })

        return listEmployeeActivityMvcImpl.root
    }


    override fun onUserTouchUserPreview(id: Int) {
        try {
            listEmployee.forEach{
                if (it.ID!! == id) {
                    EmployeeSelected.employee = it
                }
            }
//            CustomerDetailsActivity.start(layoutInflater.context)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onResume() {
        super.onResume()
        CustomerSelected.reset()
        listEmployeeActivityMvcImpl.verifyIfUpdateDataBase()
    }
}