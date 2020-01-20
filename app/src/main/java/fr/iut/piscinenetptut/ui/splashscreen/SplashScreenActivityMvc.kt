package fr.iut.piscinenetptut.ui.splashscreen

import fr.iut.piscinenetptut.entities.Register

interface SplashScreenActivityMvc {

    fun onRegisterInformationIdLoaded(register: Register)
    interface listeners {
        fun onUserWantToLogin()
    }
}