package fr.iut.piscinenetptut.ui.splashscreen

interface SplashScreenActivityMvc {

    fun onRegisterInformationIdLoaded()
    interface Listeners {
        fun onUserWantToLogin()
        fun onForgottenPassword()
    }
}