package fr.iut.piscinenetptut.ui.managementCustomer.swimmingpool

import android.content.Intent

interface SwimmingPoolFragmentMvc {

    fun verifyAllInput()
    fun verifyIfAllInputTextAreNotEmpty(): Boolean
    fun verifyIfAllRadioGroupAreSelected(): Boolean
    fun verifyIfPictureIsSelected(): Boolean
    fun verifyIfOtherParametersAreSelected(): Boolean
    fun showPicture(data: Intent?)

    fun onUserWantToShowDetailPoolToUpdate()

    interface Listener {
    }
}