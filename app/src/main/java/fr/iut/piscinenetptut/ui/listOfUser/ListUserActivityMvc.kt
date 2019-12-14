package fr.iut.piscinenetptut.ui.listOfUser

import fr.iut.piscinenetptut.entities.User

interface ListUserActivityMvc {

    fun onUserListLoaded(users: ArrayList<User>)

    interface Listeners {
        fun onUserTouchUserPreview(userId: String)
    }
}