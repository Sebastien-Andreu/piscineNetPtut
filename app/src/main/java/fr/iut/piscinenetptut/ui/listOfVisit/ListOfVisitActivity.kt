package fr.iut.piscinenetptut.ui.listOfVisit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.home.HomeActivity
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity

class ListOfVisitActivity: AppCompatActivity(), ListOfVisitActivityMvc.Listeners {

    companion object {
        val TAG: String = "ListOfVisitActivity"

        fun start(context: Context) {
            context.startActivity(Intent(context, ListOfVisitActivity::class.java))
        }
    }

    private lateinit var listOfVisitActivityMvcImpl: ListOfVisitActivityMvcImpl
    var listOfVisitActivityViewModel = ListOfVisitActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            listOfVisitActivityMvcImpl = ListOfVisitActivityMvcImpl(this, this)
            listOfVisitActivityViewModel = ListOfVisitActivityViewModel()

            setContentView(listOfVisitActivityMvcImpl.root)

            listOfVisitActivityViewModel.visitCallBack.observe(this, Observer {
                listOfVisitActivityMvcImpl.onVisitListLoaded(it)
            })

            listOfVisitActivityViewModel.onNeedToGetVisitList()
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserTouchVisitPreview(visitID: String) {
        try {

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onBackPressed() {
        this@ListOfVisitActivity.finish()
//        HomeActivity.start(this)
    }
}