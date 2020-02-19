package fr.iut.piscinenetptut.ui.managementEmployee

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FileDataPart
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Employee
import fr.iut.piscinenetptut.entities.EmployeeSelected
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.isEmail
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.mail.SendMailCreateNewCustomer
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*
import kotlin.math.floor

class ManagementEmployeeMvcImpl (
    val managementEmployeeActivity: ManagementEmployeeActivity,
    val context: Context
): ManagementEmployeeMvc {

    val TAG: String = "ManagementEmployeeMvcImpl"

    private val json = Json(JsonConfiguration.Stable)
    private val requestHttp = httpRequest()

    var root: View? = null

    lateinit var employee: Employee

    val filePicture: MutableLiveData<Uri> = MutableLiveData()


    init {
        try {
            root = View.inflate(context, R.layout.activity_add_employee, null)

            if (null != root) {

                root?.findViewById<Button>(R.id.addEmployeeButtonAddPicture)?.setOnClickListener {
                    if (verifyPermissionCamera() || this.managementEmployeeActivity.permissionResult){
                        try {
                            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            managementEmployeeActivity.startActivityForResult(i, 1463)
                        }catch (exception: Exception) {
                            exception.toTreatFor("Camera")
                        }
                    }
                }

                root?.findViewById<Button>(R.id.addEmployeeButton)?.setOnClickListener {
                    if (verifyIfAllInputTextAreNotEmpty()){
                        managementEmployeeActivity.onUserWantToAddEmployee()
                    } else {
                        Toast.makeText(managementEmployeeActivity, "Input missing", Toast.LENGTH_LONG).show()
                    }
                }
                root?.findViewById<EditText>(R.id.addEmployeeMail)?.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

                    override fun afterTextChanged(editable: Editable) {
                        if (!root?.findViewById<EditText>(R.id.addEmployeeMail)?.text.toString().isEmail()) {
                            root?.findViewById<EditText>(R.id.addEmployeeMail)?.setTextColor(Color.RED)
                        } else {
                            root?.findViewById<EditText>(R.id.addEmployeeMail)?.setTextColor(Color.BLACK)
                        }
                    }
                })


                /*-----------------------UPDATE-----------------------*/
                if (EmployeeSelected.employee.ID != null) {
                    root!!.findViewById<Button>(R.id.addEmployeeButton)?.visibility = View.GONE

                    root!!.findViewById<LinearLayout>(R.id.updateEmployeeLayout)?.visibility = View.VISIBLE
                    root!!.findViewById<Button>(R.id.updateEmployeeButton)?.setOnClickListener{
                        if (verifyIfAllInputTextAreNotEmpty()){
                            managementEmployeeActivity.onUserWantToModifyEmployeeInformation()
                        }
                    }
                    root!!.findViewById<Button>(R.id.updateEmployeeButtonCancel)?.setOnClickListener{
                        managementEmployeeActivity.onBackPressed()
                    }

                    onUserWantToShowDetailEmployeeToUpdate()
                }
                /*-----------------------UPDATE-----------------------*/
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onUserWantToShowDetailEmployeeToUpdate() {
        managementEmployeeActivity.managementEmployeeViewModel.showInformationOfEmployeeWhenUserWantToUpdate(root!!)
    }

    private fun verifyIfAllInputTextAreNotEmpty(): Boolean{
        return ( !root?.findViewById<TextView>(R.id.addEmployeeName)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addEmployeeSurname)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addEmployeeAddr)?.text.isNullOrEmpty() &&
                 !root?.findViewById<TextView>(R.id.addEmployeeMail)?.text.isNullOrEmpty() &&
                  root?.findViewById<EditText>(R.id.addEmployeeMail)?.text.toString().isEmail() &&
                 !root?.findViewById<TextView>(R.id.addEmployeeTelPhoneNumber)?.text.isNullOrEmpty() &&
                  managementEmployeeActivity.uriPicture != null)
    }

    override fun addEmployee(employee: Employee) {
        try {
            if (null != root) {
                this.employee = employee

                val file : String? = managementEmployeeActivity.uriPicture

                if (file != null){
                    Fuel.upload(requestHttp.url + "Picture").add{ FileDataPart(File(file), name = "picture", filename=employee.picture) }
                        .response { result ->
                            println(result)
                        }
                }

                Fuel.post(requestHttp.url+"Employee")
                    .body(requestHttp.convertData(json.stringify(Employee.serializer(), employee)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({
                            createLoginForEmployee((employee.surname.toString()[0]) + "." + employee.name)
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun updateEmployee() {
        try {
            if (null != root) {

                val file : String? = managementEmployeeActivity.uriPicture

                if (file != null){
                    Fuel.upload(requestHttp.url + "Picture").add{ FileDataPart(File(file), name = "picture", filename= EmployeeSelected.employee.picture) }
                        .response { result ->
                            println(result)
                        }
                }

                Fuel.put(requestHttp.url+"Employee/" + EmployeeSelected.employee.ID)
                    .body(requestHttp.convertData(json.stringify(Employee.serializer(), EmployeeSelected.employee)))
                    .header("Content-Type" to "application/x-www-form-urlencoded")
                    .responseString { _, _, result ->
                        result.fold({
                            Toast.makeText(managementEmployeeActivity, "Updated !", Toast.LENGTH_LONG).show()
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }


    override fun createLoginForEmployee(login: String) {
        Fuel.get(requestHttp.url+"Register")
            .responseString { _, _, result ->
                result.fold({ d ->
                    verifyValidifyOfLogin(json.parse(Register.serializer().list,d), login)
                }, { err ->
                    println(err.message)
                })
            }
    }

    private fun verifyValidifyOfLogin(listRegister: List<Register>, login: String){
        var validityOfLogin = true

        listRegister.forEach {
            if (it.login == login){
                validityOfLogin = false
            }
        }
        if (validityOfLogin){
            val register = Register (
                login = login,
                mail = employee.mail,
                role = "employee"
            )
            val mail = SendMailCreateNewCustomer(register)
            mail.send(managementEmployeeActivity)
        } else {
            verifyValidifyOfLogin(listRegister, login + getAleaNumber())
        }
    }

    private fun getAleaNumber(): String{
        val chars = "0123456789"
        var passWord = ""
        for (i in 0..2) {
            passWord += chars[floor(Math.random() * chars.length).toInt()]
        }
        return passWord
    }

    /* PICTURE */
    override fun showPicture(data: Intent?){
        val picture = data?.extras?.get("data") as Bitmap
        val imageView: ImageView = root?.findViewById(R.id.addEmployePicture) as ImageView
        imageView.setImageBitmap(picture)
        imageView.visibility = View.VISIBLE

        bitmapToFile(picture)
    }

    private fun bitmapToFile(bitmap: Bitmap) {
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
                if (this.managementEmployeeActivity.permissionCamera.neverAskAgainSelected(this.managementEmployeeActivity, Manifest.permission.CAMERA)) {
                    displayNeverAskAgainDialog()
                } else {
                    this.managementEmployeeActivity.requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
                }
            }
        }
        return true
    }


    private fun displayNeverAskAgainDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
        builder.setMessage(R.string.warning_permission_camera)
        builder.setCancelable(false)
        builder.setPositiveButton(R.string.permiteManually) { dialog, _ ->
            dialog.dismiss()
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", this.context.packageName, null)
            intent.data = uri
            ContextCompat.startActivity(this.context, intent, null)
        }
        builder.setNegativeButton(R.string.cancelPermission, null)
        builder.show()
    }
    /* PERMISSION */
}