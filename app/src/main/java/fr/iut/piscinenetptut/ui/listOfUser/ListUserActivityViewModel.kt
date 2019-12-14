package fr.iut.piscinenetptut.ui.listOfUser

import androidx.lifecycle.MutableLiveData
import fr.iut.piscinenetptut.entities.User
import fr.iut.piscinenetptut.library.extension.toTreatFor

class ListUserActivityViewModel {
    val TAG: String = "ListUserActivityViewModel"

    val usersCallBack: MutableLiveData<ArrayList<User>> = MutableLiveData()

    fun onNeedToGetUserList() {
        try {
            val tempUsers: ArrayList<User> = arrayListOf(
                User (
                    poolPictureId = 0,
                    firstName = "Théo",
                    surName = "Bouteiller",
                    isWarnings = true
                ),
                User (
                    poolPictureId = 0,
                    firstName = "Sébastien",
                    surName = "Andreux",
                    isWarnings = true
                ),
                User (
                    poolPictureId = 0,
                    firstName = "Thomas",
                    surName = "Harel",
                    isWarnings = false
                ),
                User (
                    poolPictureId = 0,
                    firstName = "Loic",
                    surName = "Botella",
                    isWarnings = false
                ),
                User (
                    poolPictureId = 0,
                    firstName = "Lucas",
                    surName = "Dugenne",
                    isWarnings = true
                ),
                User (
                    poolPictureId = 0,
                    firstName = "Thomas",
                    surName = "Fillols",
                    isWarnings = false
                )
            )

            usersCallBack.postValue(tempUsers)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}