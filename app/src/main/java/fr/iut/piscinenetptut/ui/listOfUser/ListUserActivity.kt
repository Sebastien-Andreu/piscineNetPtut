package fr.iut.piscinenetptut.ui.listOfUser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.library.extension.toDebug
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.customerdetails.CustomerDetailsActivity

class ListUserActivity: AppCompatActivity(), ListUserActivityMvc.Listeners {

    companion object {
        val TAG: String = "ListUserActivity"

        fun start(context: Context) {
            context.startActivity(Intent(context, ListUserActivity::class.java))
        }
    }

    lateinit var listUserActivityMvcImpl: ListUserActivityMvcImpl
    var listUserActivityViewModel = ListUserActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            listUserActivityMvcImpl = ListUserActivityMvcImpl(this, this)
            listUserActivityViewModel = ListUserActivityViewModel()

            setContentView(listUserActivityMvcImpl.root)

            listUserActivityViewModel.usersCallBack.observe(this, Observer {
                listUserActivityMvcImpl.onUserListLoaded(it)
            })

            listUserActivityViewModel.onNeedToGetUserList()
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserTouchUserPreview(userId: String) {
        try {
            "userId : $userId".toDebug("COUCOU")
            this@ListUserActivity.finish()
            CustomerDetailsActivity.start(this)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}