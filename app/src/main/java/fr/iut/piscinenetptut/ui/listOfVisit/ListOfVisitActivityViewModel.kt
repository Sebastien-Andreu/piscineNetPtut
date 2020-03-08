package fr.iut.piscinenetptut.ui.listOfVisit

import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.entities.Visit
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list

class ListOfVisitActivityViewModel {

    val TAG: String = "ListOfVisitActivityViewModel"

    val visitCallBack: MutableLiveData<List<Visit>> = MutableLiveData()

    fun onNeedToGetVisitList() {
        try {
            val json = Json(JsonConfiguration.Stable)
            val requestHttp = httpRequest()

            Fuel.get(requestHttp.url+"Visit")
                .responseString { _, _, result ->
                    result.fold({ d ->
                        val listVisit : List<Visit> = json.parse(Visit.serializer().list,d)
                        visitCallBack.postValue(listVisit)
                    }, { err ->
                        println(err.message)
                    })
                }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}