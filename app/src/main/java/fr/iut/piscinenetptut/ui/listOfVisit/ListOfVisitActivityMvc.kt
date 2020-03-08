package fr.iut.piscinenetptut.ui.listOfVisit

import fr.iut.piscinenetptut.entities.Visit

interface ListOfVisitActivityMvc {

    fun onVisitListLoaded(visits: List<Visit>)
    fun verifyIfUpdateDataBase()

    fun getMaintenanceWhenSelectVisit()
    fun getTechnicalWhenSelectVisit()
    fun getObservationWhenSelectVisit()

    interface Listeners{
        fun onUserTouchVisitPreview(id: Int)
    }
}