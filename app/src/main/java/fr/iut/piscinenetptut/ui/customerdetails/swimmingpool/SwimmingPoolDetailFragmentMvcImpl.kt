package fr.iut.piscinenetptut.ui.customerdetails.swimmingpool

import android.content.Context
import android.view.View
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.customerdetails.CustomerDetailsActivity
import java.lang.Exception

class SwimmingPoolDetailFragmentMvcImpl (
    private val context: Context,
    private val swimmingPoolDetailFragment: SwimmingPoolDetailFragment
    ): SwimmingPoolDetailFragmentMvc {

    private val TAG: String = "SwimmingPoolDetailFragmentMvcImpl"

    var root: View? = null

    lateinit var swimmingPoolDetailFragmentViewModel: SwimmingPoolDetailFragmentViewModel

    init {
        try {
            root = View.inflate(context, R.layout.fragment_swimming_pool, null)

            swimmingPoolDetailFragmentViewModel = SwimmingPoolDetailFragmentViewModel()

            onUserWantToShowDetailPool((swimmingPoolDetailFragment.activity as CustomerDetailsActivity).pool)
        } catch (exception : Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToShowDetailPool(pool: Pool){
        swimmingPoolDetailFragmentViewModel.showDetailOfPool(root!!, pool)
    }

}