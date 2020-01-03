package fr.iut.piscinenetptut.ui.addCustomer.swimmingpool

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import androidx.fragment.app.Fragment
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.ui.addCustomer.customer.CustomerFragment
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

        root?.findViewById<Button>(R.id.buttonAddPicturePool)?.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 1463)
        }

        root?.findViewById<Switch>(R.id.switchAddPoolPH)?.setOnCheckedChangeListener {_,  isChecked ->
            if (isChecked) {
                root.findViewById<LinearLayout>(R.id.layoutAddPoolPH).visibility = View.VISIBLE
                return@setOnCheckedChangeListener
            }
            root.findViewById<LinearLayout>(R.id.layoutAddPoolPH).visibility = View.GONE
        }

        root?.findViewById<Switch>(R.id.switchAddPoolPompe)?.setOnCheckedChangeListener {_,  isChecked ->
            if (isChecked) {
                root.findViewById<LinearLayout>(R.id.layoutAddPoolPompe).visibility = View.VISIBLE
                return@setOnCheckedChangeListener
            }
            root.findViewById<LinearLayout>(R.id.layoutAddPoolPompe).visibility = View.GONE
        }

        root?.findViewById<Switch>(R.id.switchAddPoolRemp)?.setOnCheckedChangeListener {_,  isChecked ->
            if (isChecked) {
                root.findViewById<LinearLayout>(R.id.layoutAddPoolRemp).visibility = View.VISIBLE
                return@setOnCheckedChangeListener
            }
            root.findViewById<LinearLayout>(R.id.layoutAddPoolRemp).visibility = View.GONE
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1463){
            val picture = data?.extras?.get("data") as Bitmap
            val imageView: ImageView = this.activity?.findViewById(R.id.imageViewPicturePool) as ImageView
            imageView.setImageBitmap(picture)
            imageView.visibility = View.VISIBLE
        }
    }
}
