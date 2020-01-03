package fr.iut.piscinenetptut.ui.addCustomer.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Switch
import fr.iut.piscinenetptut.R

class CustomerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View? = inflater.inflate(R.layout.fragment_add_customer, container, false)

        root?.findViewById<Switch>(R.id.addCustomerSwitchGuardian)?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                root.findViewById<LinearLayout>(R.id.layoutGuardian).visibility = View.VISIBLE
                return@setOnCheckedChangeListener
            }
            root.findViewById<LinearLayout>(R.id.layoutGuardian).visibility = View.GONE
        }

        return root
    }
}
