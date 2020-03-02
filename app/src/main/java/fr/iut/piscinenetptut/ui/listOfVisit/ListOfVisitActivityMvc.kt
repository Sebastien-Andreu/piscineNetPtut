package fr.iut.piscinenetptut.ui.listOfVisit

interface ListOfVisitActivityMvc {

//    fun onVisitListLoaded(visits: ArrayList<Visit>)

    interface Listeners{
        fun onUserTouchVisitPreview(visitID: String)
    }
}