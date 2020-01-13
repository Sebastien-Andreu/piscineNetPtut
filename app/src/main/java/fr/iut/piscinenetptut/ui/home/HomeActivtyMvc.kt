package fr.iut.piscinenetptut.ui.home

interface HomeActivtyMvc {

    interface listenners {
        fun onUserWantToAddAClient()
        fun onUserWantToSeeAllClient()
        fun onUserWanttoSeeAllVisit()
    }
}