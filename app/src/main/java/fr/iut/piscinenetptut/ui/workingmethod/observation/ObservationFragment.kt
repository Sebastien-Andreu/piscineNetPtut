package fr.iut.piscinenetptut.ui.workingmethod.observation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity

class ObservationFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root:View = inflater.inflate(R.layout.fragment_observation, container, false)

        root.findViewById<Button>(R.id.finalizeVisitButton).setOnClickListener {
            this@ObservationFragment.activity!!.finish()
            this.context?.let { ListCustomerActivity.start(it) }
        }

        return root
    }
}