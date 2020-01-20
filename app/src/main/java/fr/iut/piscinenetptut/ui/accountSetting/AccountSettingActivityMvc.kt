package fr.iut.piscinenetptut.ui.accountSetting

interface AccountSettingActivityMvc {

    fun verifyIfAllInputPassIsNotEmpty(): Boolean
    fun verifyIfAllInputMailIsNotEmpty(): Boolean

    fun verifyDataOfPassword(): Boolean
    fun verifyDataOfMail(): Boolean

    fun updatePassword()
    fun updateMail()

    interface Listenners {
    }
}