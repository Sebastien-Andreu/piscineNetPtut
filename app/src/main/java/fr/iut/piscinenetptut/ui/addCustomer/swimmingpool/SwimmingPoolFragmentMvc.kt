package fr.iut.piscinenetptut.ui.addCustomer.swimmingpool

import android.content.Intent

interface SwimmingPoolFragmentMvc {

    fun verifyAllInput()
    fun verifyIfAllInputTextAreNotEmpty(): Boolean
    fun verifyIfAllRadioGroupAreSelected(): Boolean
    fun verifyIfPictureIsSelected(): Boolean
    fun verifyIfOtherParametersAreSelected(): Boolean
    fun showPicture(data: Intent?)

    interface Listener {

    }
}