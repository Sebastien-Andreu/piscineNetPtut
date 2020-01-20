package fr.iut.piscinenetptut.ui.managementCustomer.swimmingpool

import android.graphics.BitmapFactory
import android.view.View
import android.widget.*
import androidx.core.view.forEach
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.entities.Pool
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.customView.RecursiveRadioGroup
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest

class SwimmingPoolFragmentViewModel {

    private var TAG: String = "SwimmingPoolFragmentViewModel"

    private var root: View? = null

    private val requestHttp = httpRequest()


    fun showInformationOfPoolWhenUserWantToUpdate(root: View) {
        try {
            this.root = root
            val pool = CustomerSelected.pool

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
            root.findViewById<EditText>(R.id.addPoolDistance)?.setText(pool.distance)
            root.findViewById<EditText>(R.id.addPoolDateElectronicalProduct)?.setText(pool.dateElectronicalProduct)

            setRecursiveRadioButtonSelected(R.id.addPoolShape, pool.shape!!)
            setRecursiveRadioButtonSelected(R.id.addPoolEnvironment, pool.environment!!)
            setRecursiveRadioButtonSelected(R.id.addPoolState, pool.state!!)
            setRecursiveRadioButtonSelected(R.id.addPoolTypeOfCover, pool.cover!!)
            setRadioButtonSelected(R.id.addPoolAcces, pool.acces!!)
            setRadioButtonSelected(R.id.addPoolElectronicalProduct, pool.electronicalProduct!!)

            setTextOfPossibleNullText(R.id.dateSandFilter, pool.dateSandFilter!!)
            setTextOfPossibleNullText(R.id.brandSandFilter, pool.brandSandFilter!!)
            setTextOfPossibleNullText(R.id.addPoolBrandFilter, pool.brandFilter!!)
            setTextOfPossibleNullText(R.id.cvFilter, pool.cvFilter!!)
            setTextOfPossibleNullText(R.id.dateFilter, pool.dateFilter!!)
            setTextOfPossibleNullText(R.id.addPoolDateElectronicalProduct, pool.dateElectronicalProduct!!)
            setTextOfPossibleNullText(R.id.addPoolObservation, pool.observation!!)

            if (pool.warning != false.toString()){
                root.findViewById<Switch>(R.id.addPoolWarning)?.isChecked = true
            }

            if (pool.winterCover != false.toString()){
                root.findViewById<Switch>(R.id.addPoolWinterCover)?.isChecked = true
            }

            if (pool.datePH.toString() != null.toString()){
                root.findViewById<Switch>(R.id.switchAddPoolPH)?.isChecked = true
                root.findViewById<LinearLayout>(R.id.layoutAddPoolPH)?.visibility = View.VISIBLE
                root.findViewById<EditText>(R.id.addPoolDatePH)?.setText(pool.datePH)
            }

            if (pool.datePomp.toString() != null.toString()){
                root.findViewById<Switch>(R.id.switchAddPoolPompe)?.isChecked = true
                root.findViewById<LinearLayout>(R.id.layoutAddPoolPompe)?.visibility = View.VISIBLE
                root.findViewById<EditText>(R.id.addPoolDatePompe)?.setText(pool.datePomp)
            }

            if (pool.dateRemp.toString() != null.toString()){
                root.findViewById<Switch>(R.id.switchAddPoolRemp)?.isChecked = true
                root.findViewById<LinearLayout>(R.id.layoutAddPoolRemp)?.visibility = View.VISIBLE
                root.findViewById<EditText>(R.id.addPoolDateRemp)?.setText(pool.dateRemp)
            }

            if (pool.observation != null.toString()){
                root.findViewById<EditText>(R.id.addPoolObservation)?.setText(pool.observation)
            }


        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    private fun setRecursiveRadioButtonSelected(id: Int, str: String) {
        root!!.findViewById<RecursiveRadioGroup>(id)?.forEach {
            (it as LinearLayout).forEach {it2 ->
                if ((it2 as RadioButton).text == str){
                    root!!.findViewById<RadioButton>(it2.id).isChecked = true
                }
            }
        }
    }

    private fun setRadioButtonSelected(id: Int, str: String) {
        root!!.findViewById<RadioGroup>(id)?.forEach {
            if ((it as RadioButton).text == str){
                root!!.findViewById<RadioButton>(it.id).isChecked = true
            }
        }
    }

    private fun setTextOfPossibleNullText(id: Int, str: String){
        if (str != null.toString()){
            root!!.findViewById<EditText>(id)?.setText(str)
        }
    }
}