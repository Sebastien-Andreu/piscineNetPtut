package fr.iut.piscinenetptut.ui.addCustomer.swimmingpool

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.shared.customView.RecursiveRadioGroup
import fr.iut.piscinenetptut.ui.addCustomer.AddCustomerActivity


class SwimmingPoolFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View? = inflater.inflate(R.layout.fragment_add_pool, container, false)

        root?.findViewById<Button>(R.id.addCustomerButton)?.setOnClickListener {
            verifyAllInput()
//            this@SwimmingPoolFragment.activity!!.finish()
//            this.context?.let { ListUserActivity.start(it) }
        }

        root?.findViewById<Button>(R.id.buttonAddPicturePool)?.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 1463)
        }

        root?.findViewById<Switch>(R.id.switchAddPoolPH)?.setOnCheckedChangeListener {_,  isChecked ->
            if (isChecked) {
                root.findViewById<LinearLayout>(R.id.layoutAddPoolPH).visibility = View.VISIBLE
                root.findViewById<TextView>(R.id.addPoolDatePH)?.text = null
                return@setOnCheckedChangeListener
            }
            root.findViewById<LinearLayout>(R.id.layoutAddPoolPH).visibility = View.GONE
        }

        root?.findViewById<Switch>(R.id.switchAddPoolPompe)?.setOnCheckedChangeListener {_,  isChecked ->
            if (isChecked) {
                root.findViewById<LinearLayout>(R.id.layoutAddPoolPompe).visibility = View.VISIBLE
                root.findViewById<TextView>(R.id.addPoolDatePompe)?.text = null
                return@setOnCheckedChangeListener
            }
            root.findViewById<LinearLayout>(R.id.layoutAddPoolPompe).visibility = View.GONE
        }

        root?.findViewById<Switch>(R.id.switchAddPoolRemp)?.setOnCheckedChangeListener {_,  isChecked ->
            if (isChecked) {
                root.findViewById<LinearLayout>(R.id.layoutAddPoolRemp).visibility = View.VISIBLE
                root.findViewById<TextView>(R.id.addPoolDateRemp)?.text = null
                return@setOnCheckedChangeListener
            }
            root.findViewById<LinearLayout>(R.id.layoutAddPoolRemp).visibility = View.GONE
        }

        return root
    }

    /*      Use to show picture     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1463){
            val picture = data?.extras?.get("data") as Bitmap
            val imageView: ImageView = this.activity?.findViewById(R.id.addPoolPicture) as ImageView
            imageView.setImageBitmap(picture)
            imageView.visibility = View.VISIBLE
        }
    }

    private fun verifyAllInput(){
        if (verifyIfPictureIsSelected() && verifyIfAllInputTextAreNotEmpty() && verifyIfAllRadioGroupAreSelected() && verifyIfOtherParametersAreSelected()){
            (this@SwimmingPoolFragment.activity as AddCustomerActivity).onUserWantToAddNewCustomer()
        } else {
            Toast.makeText(this@SwimmingPoolFragment.activity, "pas bon", Toast.LENGTH_LONG).show()
        }
    }

    private fun verifyIfAllInputTextAreNotEmpty(): Boolean {
        return ( !this@SwimmingPoolFragment.activity?.findViewById<TextView>(R.id.addPoolLo)?.text.isNullOrEmpty() &&
                 !this@SwimmingPoolFragment.activity?.findViewById<TextView>(R.id.addPoolLa)?.text.isNullOrEmpty() &&
                 !this@SwimmingPoolFragment.activity?.findViewById<TextView>(R.id.addPoolDepth)?.text.isNullOrEmpty() &&
                 !this@SwimmingPoolFragment.activity?.findViewById<TextView>(R.id.addPoolDistance)?.text.isNullOrEmpty())
    }

    private fun verifyIfAllRadioGroupAreSelected(): Boolean{
        return ( this@SwimmingPoolFragment.activity?.findViewById<RecursiveRadioGroup>(R.id.addPoolEnvironment)?.checkedItemId != null &&
                 this@SwimmingPoolFragment.activity?.findViewById<RecursiveRadioGroup>(R.id.addPoolState)?.checkedItemId != null &&
                 this@SwimmingPoolFragment.activity?.findViewById<RecursiveRadioGroup>(R.id.addPoolTypeOfCover)?.checkedItemId != null &&
                 this@SwimmingPoolFragment.activity?.findViewById<RadioGroup>(R.id.addPoolAcces)?.checkedRadioButtonId != -1 &&
                 this@SwimmingPoolFragment.activity?.findViewById<RadioGroup>(R.id.addPoolElectronicalProduct)?.checkedRadioButtonId != -1)
    }

    private fun verifyIfPictureIsSelected(): Boolean{
        return this@SwimmingPoolFragment.activity?.findViewById<ImageView>(R.id.addPoolPicture)?.drawable != null
    }

    private fun verifyIfOtherParametersAreSelected(): Boolean{
        if (this@SwimmingPoolFragment.activity?.findViewById<LinearLayout>(R.id.layoutAddPoolPH)?.visibility == View.VISIBLE){
            if (this@SwimmingPoolFragment.activity?.findViewById<TextView>(R.id.addPoolDatePH)?.text.isNullOrEmpty()){
                return false
            }
        }
        if (this@SwimmingPoolFragment.activity?.findViewById<LinearLayout>(R.id.layoutAddPoolPompe)?.visibility == View.VISIBLE) {
            if (this@SwimmingPoolFragment.activity?.findViewById<TextView>(R.id.addPoolDatePompe)?.text.isNullOrEmpty()) {
                return false
            }
        }
        if (this@SwimmingPoolFragment.activity?.findViewById<LinearLayout>(R.id.layoutAddPoolRemp)?.visibility == View.VISIBLE) {
            return !this@SwimmingPoolFragment.activity?.findViewById<TextView>(R.id.addPoolDateRemp)?.text.isNullOrEmpty()
        }
        return true
    }
}
