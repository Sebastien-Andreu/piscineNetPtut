package fr.iut.piscinenetptut.ui.customerdetails.swimmingpool

import fr.iut.piscinenetptut.entities.Pool

interface SwimmingPoolDetailFragmentMvc {
    fun onUserWantToShowDetailPool(pool: Pool)
    interface Listener{
    }
}