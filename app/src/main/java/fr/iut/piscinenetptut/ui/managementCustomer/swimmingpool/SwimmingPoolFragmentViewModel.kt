package fr.iut.piscinenetptut.ui.managementCustomer.swimmingpool

import android.graphics.BitmapFactory
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest

class SwimmingPoolFragmentViewModel {

    private var TAG: String = "SwimmingPoolFragmentViewModel"

    private val requestHttp = httpRequest()


    fun showInformationOfPoolWhenUserWantToUpdate(root: View, pool: Pool) {
        try {
            Fuel.get(requestHttp.url + "Picture/${pool.picture}")
                .response { request, response, result ->
                    val (bytes, error) = result
                    if (bytes != null) {
                        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        root.findViewById<ImageView>(R.id.addPoolPicture)?.let {
                            it.setImageBitmap(bitmap)
                            it.visibility = View.VISIBLE
                        }
                    }
                }
            root.findViewById<EditText>(R.id.addPoolLo)?.setText(pool.sizeLo)
            root.findViewById<EditText>(R.id.addPoolLa)?.setText(pool.sizeLa)
            root.findViewById<EditText>(R.id.addPoolDepth)?.setText(pool.depth)
//                root.findViewById<RadioButton>(idShape!!)?
//                environment = root.findViewById<RadioButton>(idEnvironment!!)?
//                state = root.findViewById<RadioButton>(idState!!)
//                cover = root.findViewById<RadioButton>(idCover!!)?
            root.findViewById<EditText>(R.id.addPoolDistance)?.setText(pool.distance)
//                root.findViewById<Switch>(R.id.addPoolWarning)?.setOn
//                acces = root.findViewById<RadioButton>(idAcces!!)?.setText(pool.sizeLo)
//                winterCover = root.findViewById<TextView>(R.id.addPoolWinterCover)?.setText(pool.sizeLo) // pas bn ---------------------------------

//                root.findViewById<EditText>(R.id.dateSandFilter)?.setText(pool.dateSandFilter) if not null
//                root.findViewById<EditText>(R.id.brandSandFilter)?.setText(pool.brandSandFilter)

//                root.findViewById<EditText>(R.id.addPoolBrandFilter)?.setText(pool.brandFilter)
//                root.findViewById<EditText>(R.id.cvFilter)?.setText(pool.cvFilter)
//                root.findViewById<EditText>(R.id.dateFilter)?.setText(pool.dateFilter)


//                root.findViewById<RadioButton>(idElectronicalProduct!!)?.setText(pool.sizeLo)
            root.findViewById<EditText>(R.id.addPoolDateElectronicalProduct)
                ?.setText(pool.dateElectronicalProduct)
//                root.findViewById<EditText>(R.id.addPoolDatePH)?.setText(pool.sizeLo)
//                root.findViewById<EditText>(R.id.addPoolDatePompe)?.setText(pool.sizeLo)
//                root.findViewById<EditText>(R.id.addPoolDateRemp)?.setText(pool.sizeLo)
            root.findViewById<EditText>(R.id.addPoolObservation)?.setText(pool.observation)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }
}