package fr.iut.piscinenetptut.ui.listOfVisit

import androidx.lifecycle.MutableLiveData
import fr.iut.piscinenetptut.entities.Visit
import fr.iut.piscinenetptut.library.extension.toTreatFor

class ListOfVisitActivityViewModel {

    val TAG: String = "ListOfVisitActivityViewModel"

    val visitCallBack: MutableLiveData<ArrayList<Visit>> = MutableLiveData()

    fun onNeedToGetVisitList() {
        try {
            val tempUsers: ArrayList<Visit> = arrayListOf(
                Visit(
                    firstnameCustomer = "Théo",
                    surnameCustomer = "Bouteiller",
                    isWarnings = true
                ),
                Visit(
                    firstnameCustomer = "Sebastien",
                    surnameCustomer = "Andreu",
                    isWarnings = true
                ),
                Visit(
                    firstnameCustomer = "Thomas",
                    surnameCustomer = "Harel",
                    isWarnings = false
                ),
                Visit(
                    firstnameCustomer = "Loic",
                    surnameCustomer = "Botella",
                    isWarnings = false
                ),
                Visit(
                    firstnameCustomer = "Lucas",
                    surnameCustomer = "Dugenne",
                    isWarnings = true
                ),
                Visit(
                    firstnameCustomer = "Thomas",
                    surnameCustomer = "Fillols",
                    isWarnings = false
                )
            )

            visitCallBack.postValue(tempUsers)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}