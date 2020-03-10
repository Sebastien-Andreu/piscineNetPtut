package fr.iut.piscinenetptut.ui.forgottenPassword

import android.view.View

interface ForgottenPasswordMvc {

    interface Listeners{
        fun onUserWantToSendNewPassword()
    }
}