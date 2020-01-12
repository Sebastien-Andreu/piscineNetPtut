package fr.iut.piscinenetptut.ui.addCustomer.swimmingpool

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.customView.RecursiveRadioGroup
import fr.iut.piscinenetptut.ui.addCustomer.AddCustomerActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class SwimmingPoolFragmentMvcImpl (
    private val context: Context,
    private val swimmingPoolFragment: SwimmingPoolFragment
    ) : SwimmingPoolFragmentMvc {

    private val TAG = "SwimmingPoolFragmentMvcImpl"

    var root: View? = null

    val filePicture: MutableLiveData<Uri> = MutableLiveData()

    init {
        try {
            root = View.inflate(context, R.layout.fragment_add_pool, null)

            root?.findViewById<Button>(R.id.addCustomerButton)?.setOnClickListener {
                verifyAllInput()
            }

            root?.findViewById<Button>(R.id.buttonAddPicturePool)?.setOnClickListener {
                if (verifyPermissionCamera() || this.swimmingPoolFragment.permissionResult){
                    println("tu a acces")
                    try {
                        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        swimmingPoolFragment.startActivityForResult(i, 1463)
                    }catch (exception: Exception) {
                        exception.toTreatFor("Camera")
                    }
                }
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

            root?.findViewById<Switch>(R.id.addPoolWarning)?.setOnCheckedChangeListener {_,  isChecked ->
                if (isChecked) {
                    root!!.findViewById<TextView>(R.id.addPoolTextValueOfSwitchWarning)?.text = true.toString()
                    return@setOnCheckedChangeListener
                }
                root!!.findViewById<TextView>(R.id.addPoolTextValueOfSwitchWarning)?.text = false.toString()
            }

            root?.findViewById<Switch>(R.id.addPoolWinterCover)?.setOnCheckedChangeListener {_,  isChecked ->
                if (isChecked) {
                    root!!.findViewById<TextView>(R.id.addPoolTextValueOfSwitchWinterCover)?.text = true.toString()
                    return@setOnCheckedChangeListener
                }
                root!!.findViewById<TextView>(R.id.addPoolTextValueOfSwitchWinterCover)?.text = false.toString()
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
                 !root?.findViewById<TextView>(R.id.addPoolDateElectronicalProduct)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addPoolDistance)?.text.isNullOrEmpty())
    }

    override fun verifyIfAllRadioGroupAreSelected(): Boolean{
        return ( root?.findViewById<RecursiveRadioGroup>(R.id.addPoolEnvironment)?.checkedItemId != null &&
                 root?.findViewById<RecursiveRadioGroup>(R.id.addPoolState)?.checkedItemId != null &&
                 root?.findViewById<RecursiveRadioGroup>(R.id.addPoolTypeOfCover)?.checkedItemId != null &&
                 root?.findViewById<RecursiveRadioGroup>(R.id.addPoolShape)?.checkedItemId != null &&
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

    /* PICTURE */
    override fun showPicture(data: Intent?){
        val picture = data?.extras?.get("data") as Bitmap
        val imageView: ImageView = root?.findViewById(R.id.addPoolPicture) as ImageView
        imageView.setImageBitmap(picture)
        imageView.visibility = View.VISIBLE

        bitmapToFile(picture)
    }

    private fun bitmapToFile(bitmap:Bitmap) {
        val wrapper = ContextWrapper(context)

        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")

        try{
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch (e: IOException){
            e.printStackTrace()
        }

        filePicture.postValue(Uri.parse(file.absolutePath))
    }
    /* END PICTURE */



    /* PERMISSION */
    private fun verifyPermissionCamera(): Boolean{
        if (ContextCompat.checkSelfPermission(this.context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (this.swimmingPoolFragment.permissionCamera.neverAskAgainSelected(this.swimmingPoolFragment.activity!!, Manifest.permission.CAMERA)) {
                    displayNeverAskAgainDialog()
                } else {
                    this.swimmingPoolFragment.requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
                }
            }
        }
        return true
    }
    private fun displayNeverAskAgainDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
        builder.setMessage(R.string.warning_permission_camera)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.permiteManually) { dialog, which ->
            dialog.dismiss()
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", this.context.packageName, null)
            intent.data = uri
            startActivity(this.context, intent, null)
        }
        builder.setNegativeButton(R.string.cancelPermission, null)
        builder.show()
    }
    /* PERMISSION */
}