package fr.iut.piscinenetptut.ui.listOfCustomer

import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list

class ListCustomerActivityViewModel {
    private val TAG: String = "ListCustomerActivityViewModel"

    val customerCallBack: MutableLiveData<List<Customer>> = MutableLiveData()
    val poolCallBack: MutableLiveData<List<Pool>> = MutableLiveData()


    fun onNeedToGetUserList() {
        try {
            val json = Json(JsonConfiguration.Stable)
            val requestHttp = httpRequest()

            Fuel.get(requestHttp.url+"Customer")
                .responseString { _, _, result ->
                    result.fold({ d ->
                        val listCustomer: List<Customer> = json.parse(Customer.serializer().list,d)
                        customerCallBack.postValue(listCustomer)
                    }, { err ->
                        println(err.message)
                    })
                }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    fun onNeedToGetPoolList(){
        try {
            val json = Json(JsonConfiguration.Stable)
            val requestHttp = httpRequest()

            Fuel.get(requestHttp.url+"Pool")
                .responseString { _, _, result ->
                    result.fold({ d ->
                        val listPool: List<Pool> = json.parse(Pool.serializer().list,d)
                        poolCallBack.postValue(listPool)
                    }, { err ->
                        println(err.message)
                    })
                }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}