package fr.iut.piscinenetptut.ui.listOfCustomer.item

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Customer
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest


class UserPreviewFactory {
    companion object {
        private val TAG: String = "UserPreviewFactory"

        fun createUserPreviewForUser(customer: Customer, picture: String, userPreviewClickListener: View.OnClickListener, context: Context): View {
            try {
                val view: View = View.inflate(context, R.layout.user_list_user_preview, null)
                val requestHttp = httpRequest()

                view.tag = customer.ID
                view.setOnClickListener(userPreviewClickListener)


                view.findViewById<TextView>(R.id.firstNameTextView)?.text = customer.name
                view.findViewById<TextView>(R.id.surNameTextView)?.text = customer.surname


                Fuel.get(requestHttp.url+"Picture/$picture")
                    .response{ _, _, result ->
                        val (bytes, _) = result
                        if (bytes != null) {
                            val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
                            view.findViewById<ImageView>(R.id.pictureListCustomer)?.setImageBitmap(bitmap)
                        }
                    }


//                if (customer.isWarning) {
//                    view.findViewById<RelativeLayout>(R.id.warningWrapper)?.visibility =
//                        View.VISIBLE
//                }

                return view
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }

            return View(context)
        }
    }
}