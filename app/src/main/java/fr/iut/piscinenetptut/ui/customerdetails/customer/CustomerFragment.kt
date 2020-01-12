package fr.iut.piscinenetptut.ui.customerdetails.customer


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.ui.workingmethod.WorkingMethodActivity

class CustomerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View? = inflater.inflate(R.layout.fragment_customer, container, false)

        root?.findViewById<Button>(R.id.detailCustomerMakeVisitButton)?.setOnClickListener {
            this@CustomerFragment.activity!!.finish()
            this.context?.let { WorkingMethodActivity.start(it) }
        }
        return root
    }
}
