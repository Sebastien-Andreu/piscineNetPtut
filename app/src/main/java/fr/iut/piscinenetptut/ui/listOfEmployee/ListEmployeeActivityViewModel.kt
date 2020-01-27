package fr.iut.piscinenetptut.ui.listOfEmployee

import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.entities.Employee
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list

class ListEmployeeActivityViewModel {
    private val TAG: String = "ListUserActivityViewModel"

    val employeeCallBack: MutableLiveData<List<Employee>> = MutableLiveData()


    fun onNeedToGetEmployeeList() {
        try {
            val json = Json(JsonConfiguration.Stable)
            val requestHttp = httpRequest()

            Fuel.get(requestHttp.url+"Employee")
                .responseString { _, _, result ->
                    result.fold({ d ->
                        employeeCallBack.postValue(json.parse(Employee.serializer().list,d))
                    }, { err ->
                        println(err.message)
                    })
                }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}