package fr.iut.piscinenetptut.ui.customerdetails.swimmingpool

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.CustomerSelected
import fr.iut.piscinenetptut.library.extension.setMargin
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import java.lang.Exception

class SwimmingPoolDetailFragmentViewModel {

    private val TAG: String = "SwimmingPoolDetailFragmentViewModel"
    private val requestHttp = httpRequest()


    fun showDetailOfPool(root: View){
        try {
            val pool = CustomerSelected.pool

            Fuel.get(requestHttp.url+"Picture/${pool.picture}")
                .response{ _, _, result ->
                    val (bytes, _) = result
                    if (bytes != null) {
                        val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
                        root.findViewById<ImageView>(R.id.detailPoolPicture)?.setImageBitmap(bitmap)
                    }
                }

            root.findViewById<TextView>(R.id.detailPoolSize)?.text = (pool.sizeLo + " x " + pool.sizeLa)
            root.findViewById<TextView>(R.id.detailPoolDepth)?.text = (pool.depth + " m")
            root.findViewById<TextView>(R.id.detailPoolShape)?.text = pool.shape
            root.findViewById<TextView>(R.id.detailPoolEnvronment)?.text = pool.environment
            root.findViewById<TextView>(R.id.detailPoolState)?.text = pool.state
            root.findViewById<TextView>(R.id.detailPoolTypeOfCover)?.text = pool.cover
            root.findViewById<TextView>(R.id.detailPoolDistance)?.text = (pool.distance + " km")
            root.findViewById<TextView>(R.id.detailPoolAcces)?.text = pool.acces

            if (pool.brandSandFilter != null.toString()){
                root.findViewById<TextView>(R.id.detailPoolDateSandFilter)?.text = pool.dateSandFilter
                root.findViewById<TextView>(R.id.detailPoolBrandSandFilter)?.text = pool.brandSandFilter
            } else {
                root.findViewById<LinearLayout>(R.id.poolDetailLayoutBrandSandFilter)?.visibility = View.GONE
                root.findViewById<LinearLayout>(R.id.poolDetailLayoutDateSandFilter)?.visibility = View.GONE
            }

            if (pool.brandFilter != null.toString()){
                root.findViewById<TextView>(R.id.detailPoolBrandFilter)?.text = pool.brandFilter
                root.findViewById<TextView>(R.id.detailPoolCV)?.text = pool.cvFilter
                root.findViewById<TextView>(R.id.detailPoolDateFilter)?.text = pool.dateFilter
            } else {
                root.findViewById<LinearLayout>(R.id.detailPoolLayoutFiltration)?.visibility = View.GONE
            }

            root.findViewById<TextView>(R.id.detailPoolElectronicalType)?.text = pool.electronicalProduct!!
            root.findViewById<TextView>(R.id.detailPoolDateElectronicalDevice)?.text = pool.dateElectronicalProduct!!

            if (pool.warning == true.toString()){
                root.findViewById<LinearLayout>(R.id.detailPoolLayoutWarning)?.visibility = View.VISIBLE
            }
            if (pool.winterCover == true.toString()){
                root.findViewById<LinearLayout>(R.id.detailPoolLayoutWinterCover)?.visibility = View.VISIBLE
            }


            if (pool.datePH != null.toString()){
                root.findViewById<LinearLayout>(R.id.detailPoolLayoutPH)?.visibility = View.VISIBLE
                root.findViewById<TextView>(R.id.detailPoolDatePH)?.text = pool.datePH
            }
            if (pool.datePomp != null.toString()){
                root.findViewById<LinearLayout>(R.id.detailPoolLayoutHeatPump)?.visibility = View.VISIBLE
                root.findViewById<TextView>(R.id.detailPoolDateHeatPump)?.text = pool.datePomp
            }
            if (pool.dateRemp != null.toString()){
                root.findViewById<LinearLayout>(R.id.detailPoolLayoutRemp)?.visibility = View.VISIBLE
                root.findViewById<TextView>(R.id.detailPoolDateRemp)?.text = pool.dateRemp.toString()
            }

            if (pool.warning == true.toString() && pool.winterCover == true.toString()) {
                root.findViewById<LinearLayout>(R.id.detailPoolLayoutWarning)?.setMargin(null,null,null,0)
                root.findViewById<LinearLayout>(R.id.detailPoolLayoutWinterCover)?.setMargin(null,5,null,35)

            }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

}