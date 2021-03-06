package fr.iut.piscinenetptut.ui.listOfEmployee.item

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Employee
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest

class EmployeePreviewFactory {
    companion object {
        private val TAG: String = "EmployeePreviewFactory"

        fun createEmployeePreviewForUser(employee: Employee, userPreviewClickListener: View.OnClickListener, context: Context): View {
            try {
                val view: View = View.inflate(context, R.layout.employee_list_preview, null)

                view.tag = employee.ID
                view.setOnClickListener(userPreviewClickListener)


                view.findViewById<TextView>(R.id.firstNameTextView)?.text = employee.name
                view.findViewById<TextView>(R.id.surNameTextView)?.text = employee.surname

                val requestHttp = httpRequest()

                Fuel.get(requestHttp.url+"Picture/${employee.picture}")
                    .response{ _, _, result ->
                        val (bytes, _) = result
                        if (bytes != null) {
                            val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
                            view.findViewById<ImageView>(R.id.pictureListEmployee)?.setImageBitmap(bitmap)
                        }
                    }

                return view
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }

            return View(context)
        }
    }
}