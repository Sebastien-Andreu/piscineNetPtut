package fr.iut.piscinenetptut.ui.listOfCustomer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.accountSetting.AccountSettingActivity
import fr.iut.piscinenetptut.ui.customerdetails.CustomerDetailsActivity
import fr.iut.piscinenetptut.ui.home.HomeActivity


class ListCustomerActivity: AppCompatActivity(), ListCustomerActivityMvc.Listeners {

    companion object {
        val TAG: String = "ListUserActivity"

        fun start(context: Context) {
            context.startActivity(Intent(context, ListCustomerActivity::class.java))
        }
    }

    lateinit var listUserActivityMvcImpl: ListCustomerActivityMvcImpl
    var listUserActivityViewModel = ListCustomerActivityViewModel()

    lateinit var listCustomer : List<Customer>
    lateinit var listPool: List<Pool>

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            listUserActivityMvcImpl = ListCustomerActivityMvcImpl(this, this)
            listUserActivityViewModel = ListCustomerActivityViewModel()

            setContentView(listUserActivityMvcImpl.root)

            CustomerSelected.reset()

            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            listUserActivityViewModel.customerCallBack.observe(this, Observer {
                listCustomer = it
                listUserActivityViewModel.onNeedToGetPoolList()
            })

            listUserActivityViewModel.poolCallBack.observe(this, Observer {
                listPool = it
                listUserActivityMvcImpl.onUserListLoaded(listCustomer, listPool)
            })

            listUserActivityViewModel.onNeedToGetUserList()
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserTouchUserPreview(id: Int) {
        try {
            this@ListCustomerActivity.finish()

            listCustomer.forEach{
                if (it.ID!! == id) {
                    CustomerSelected.customer = it
                }
            }
            listPool.forEach{
                if (it.ID_Customer!! == id) {
                    CustomerSelected.pool = it
                }
            }
            CustomerDetailsActivity.start(this)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_setting -> {
                AccountSettingActivity.start(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        this@ListCustomerActivity.finish()
        HomeActivity.start(this)
    }
}