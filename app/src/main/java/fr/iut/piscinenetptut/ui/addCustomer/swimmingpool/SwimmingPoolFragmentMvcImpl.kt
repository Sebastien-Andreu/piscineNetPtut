package fr.iut.piscinenetptut.ui.addCustomer.swimmingpool

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.View
import android.widget.*
import fr.iut.piscinenetptut.R
import android.content.Context
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.customView.RecursiveRadioGroup
import fr.iut.piscinenetptut.ui.addCustomer.AddCustomerActivity

class SwimmingPoolFragmentMvcImpl (
    private val context: Context,
    private val swimmingPoolFragment: SwimmingPoolFragment
    ) : SwimmingPoolFragmentMvc {

    private val TAG = "SwimmingPoolFragmentMvcImpl"

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.fragment_add_pool, null)

            root?.findViewById<Button>(R.id.addCustomerButton)?.setOnClickListener {
                verifyAllInput()
//            this@SwimmingPoolFragment.activity!!.finish()
//            this.context?.let { ListUserActivity.start(it) }
            }

            root?.findViewById<Button>(R.id.buttonAddPicturePool)?.setOnClickListener {
                val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                swimmingPoolFragment.startActivityForResult(i, 1463)
            }

            root?.findViewById<Switch>(R.id.switchAddPoolPH)?.setOnCheckedChangeListener {_,  isChecked ->
                if (isChecked) {
                    root!!.findViewById<LinearLayout>(R.id.layoutAddPoolPH).visibility = View.VISIBLE
                    root!!.findViewById<TextView>(R.id.addPoolDatePH)?.text = null
                    return@setOnCheckedChangeListener
                }
                root!!.findViewById<LinearLayout>(R.id.layoutAddPoolPH).visibility = View.GONE
            }

            root?.findViewById<Switch>(R.id.switchAddPoolPompe)?.setOnCheckedChangeListener {_,  isChecked ->
                if (isChecked) {
                    root!!.findViewById<LinearLayout>(R.id.layoutAddPoolPompe).visibility = View.VISIBLE
                    root!!.findViewById<TextView>(R.id.addPoolDatePompe)?.text = null
                    return@setOnCheckedChangeListener
                }
                root!!.findViewById<LinearLayout>(R.id.layoutAddPoolPompe).visibility = View.GONE
            }

            root?.findViewById<Switch>(R.id.switchAddPoolRemp)?.setOnCheckedChangeListener {_,  isChecked ->
                if (isChecked) {
                    root!!.findViewById<LinearLayout>(R.id.layoutAddPoolRemp).visibility = View.VISIBLE
                    root!!.findViewById<TextView>(R.id.addPoolDateRemp)?.text = null
                    return@setOnCheckedChangeListener
                }
                root!!.findViewById<LinearLayout>(R.id.layoutAddPoolRemp).visibility = View.GONE
            }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun verifyAllInput(){
        if (verifyIfPictureIsSelected() && verifyIfAllInputTextAreNotEmpty() && verifyIfAllRadioGroupAreSelected() && verifyIfOtherParametersAreSelected()){
            (swimmingPoolFragment.activity as AddCustomerActivity).onUserWantToAddNewCustomer()
        } else {
            Toast.makeText(root?.context, "pas bon", Toast.LENGTH_LONG).show()
        }
    }

    override fun verifyIfAllInputTextAreNotEmpty(): Boolean {
        return ( !root?.findViewById<TextView>(R.id.addPoolLo)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addPoolLa)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addPoolDepth)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addPoolDistance)?.text.isNullOrEmpty())
    }

    override fun verifyIfAllRadioGroupAreSelected(): Boolean{
        return ( root?.findViewById<RecursiveRadioGroup>(R.id.addPoolEnvironment)?.checkedItemId != null &&
                 root?.findViewById<RecursiveRadioGroup>(R.id.addPoolState)?.checkedItemId != null &&
                 root?.findViewById<RecursiveRadioGroup>(R.id.addPoolTypeOfCover)?.checkedItemId != null &&
                 root?.findViewById<RadioGroup>(R.id.addPoolAcces)?.checkedRadioButtonId != -1 &&
                 root?.findViewById<RadioGroup>(R.id.addPoolElectronicalProduct)?.checkedRadioButtonId != -1)
    }

    override fun verifyIfPictureIsSelected(): Boolean{
        return root?.findViewById<ImageView>(R.id.addPoolPicture)?.drawable != null
    }

    override fun verifyIfOtherParametersAreSelected(): Boolean{
        if (root?.findViewById<LinearLayout>(R.id.layoutAddPoolPH)?.visibility == View.VISIBLE){
            if (root?.findViewById<TextView>(R.id.addPoolDatePH)?.text.isNullOrEmpty()){
                return false
            }
        }
        if (root?.findViewById<LinearLayout>(R.id.layoutAddPoolPompe)?.visibility == View.VISIBLE) {
            if (root?.findViewById<TextView>(R.id.addPoolDatePompe)?.text.isNullOrEmpty()) {
                return false
            }
        }
        if (root?.findViewById<LinearLayout>(R.id.layoutAddPoolRemp)?.visibility == View.VISIBLE) {
            return !root?.findViewById<TextView>(R.id.addPoolDateRemp)?.text.isNullOrEmpty()
        }
        return true
    }

    override fun showPicture(data: Intent?){
        val picture = data?.extras?.get("data") as Bitmap
        val imageView: ImageView = root?.findViewById(R.id.addPoolPicture) as ImageView
        imageView.setImageBitmap(picture)
        imageView.visibility = View.VISIBLE
    }
}