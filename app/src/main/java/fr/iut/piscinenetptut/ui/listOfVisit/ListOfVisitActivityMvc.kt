package fr.iut.piscinenetptut.ui.listOfVisit

import fr.iut.piscinenetptut.entities.Visit

interface ListOfVisitActivityMvc {

    fun onVisitListLoaded(visits: ArrayList<Visit>)

    interface Listeners{
        fun onUserTouchVisitPreview(visitID: String)
    }
}