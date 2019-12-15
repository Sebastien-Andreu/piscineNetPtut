package fr.iut.piscinenetptut.ui.addCustomer.swimmingpool

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.ui.listOfUser.ListUserActivity

class SwimmingPoolFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View? = inflater.inflate(R.layout.fragment_add_pool, container, false)

        root?.findViewById<Button>(R.id.addCustomerButton)?.setOnClickListener {
            this@SwimmingPoolFragment.activity!!.finish()
            this.context?.let { ListUserActivity.start(it) }
        }

        return root
    }
}
