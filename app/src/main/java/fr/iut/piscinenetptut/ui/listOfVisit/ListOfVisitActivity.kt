package fr.iut.piscinenetptut.ui.listOfVisit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import fr.iut.piscinenetptut.entities.Visit
import fr.iut.piscinenetptut.entities.VisitSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor

class ListOfVisitActivity: Fragment(), ListOfVisitActivityMvc.Listeners {

    val TAG: String = "ListOfVisitActivity"

    private lateinit var listOfVisitActivityMvcImpl: ListOfVisitActivityMvcImpl
    var listOfVisitActivityViewModel = ListOfVisitActivityViewModel()

    lateinit var listVisit: List<Visit>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listOfVisitActivityMvcImpl = ListOfVisitActivityMvcImpl(inflater.context, this)
        listOfVisitActivityViewModel = ListOfVisitActivityViewModel()

        listOfVisitActivityMvcImpl.listIsLoad = false

        listOfVisitActivityViewModel.visitCallBack.observe(this, Observer {
            listVisit = it
            listOfVisitActivityMvcImpl.onVisitListLoaded(listVisit)
        })

        return listOfVisitActivityMvcImpl.root
    }

    override fun onUserTouchVisitPreview(id: Int) {
        try {
            listVisit.forEach{
                if (it.ID!! == id) {
                    VisitSelected.visit = it
                    listOfVisitActivityMvcImpl.getMaintenanceWhenSelectVisit()
                }
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onResume() {
        super.onResume()
        VisitSelected.reset()
        listOfVisitActivityMvcImpl.verifyIfUpdateDataBase()
    }
}