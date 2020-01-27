package fr.iut.piscinenetptut.ui.listOfCustomer

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.customerdetails.CustomerDetailsActivity


class ListCustomerActivity: Fragment(), ListCustomerActivityMvc.Listeners {

    val TAG: String = "ListUserActivity"

    lateinit var listUserActivityMvcImpl: ListCustomerActivityMvcImpl
    var listUserActivityViewModel = ListCustomerActivityViewModel()

    lateinit var listCustomer : List<Customer>
    lateinit var listPool: List<Pool>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listUserActivityMvcImpl = ListCustomerActivityMvcImpl(inflater.context, this)
        listUserActivityViewModel = ListCustomerActivityViewModel()

        listUserActivityMvcImpl.listIsLoad = false

        listUserActivityViewModel.customerCallBack.observe(this, Observer {
            listCustomer = it
            listUserActivityViewModel.onNeedToGetPoolList()
        })

        listUserActivityViewModel.poolCallBack.observe(this, Observer {
            listPool = it
            listUserActivityMvcImpl.onUserListLoaded(listCustomer, listPool)
        })

        return listUserActivityMvcImpl.root
    }


    override fun onUserTouchUserPreview(id: Int) {
        try {
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
            CustomerDetailsActivity.start(layoutInflater.context)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onResume() {
        super.onResume()
        CustomerSelected.reset()
        listUserActivityMvcImpl.verifyIfUpdateDataBase()
    }
}