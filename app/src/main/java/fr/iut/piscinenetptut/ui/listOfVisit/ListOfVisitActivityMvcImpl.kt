package fr.iut.piscinenetptut.ui.listOfVisit

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import com.github.kittinunf.fuel.Fuel
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.*
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.shared.requestHttp.httpRequest
import fr.iut.piscinenetptut.ui.listOfVisit.item.VisitPreviewFactory
import fr.iut.piscinenetptut.ui.visitDetails.VisitDetailsActivity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class ListOfVisitActivityMvcImpl(
    val context: Context,
    val listOfVisitActivity: ListOfVisitActivity
): ListOfVisitActivityMvc {

    val TAG: String = "ListOfVisitActivityMvcImpl"

    private val requestHttp = httpRequest()
    private val json = Json(JsonConfiguration.Stable)



    private val visitPreviewClickListener: View.OnClickListener = View.OnClickListener { v ->
            if (null != v) {
                listOfVisitActivity.onUserTouchVisitPreview(v.tag.toString().toInt())
            }
        }

    var root: View? = null

    init {
        try {
            root = View.inflate(context, R.layout.activity_visit_list, null)
            listOfVisitActivity.activity?.findViewById<TextView>(R.id.textToolBar)?.text = context.getString(R.string.ListVisit)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onVisitListLoaded(visits: List<Visit>) {
        try {
            if (null != root && root!!.findViewById<LinearLayout>(R.id.listVisitWrapper)?.size == 0) {
                for (visit in visits) {
                    val view = VisitPreviewFactory.createVisitPreviewForUser(visitPreviewClickListener = visitPreviewClickListener, visit = visit, context = context)

                    root!!.findViewById<LinearLayout>(R.id.listVisitWrapper)?.addView(view)
                }
            }
        } catch (exception: Exception) {
                exception.toTreatFor(TAG)
        }
    }

    override fun verifyIfUpdateDataBase() {
        try {
            Fuel.get(requestHttp.url+"ThereIsAnUpdateForVisit")
                .responseString { _, _, result ->
                    result.fold({ d ->
                        if( Version.versionVisit < d.toInt()){
                            Version.versionVisit = d.toInt()
                            root!!.findViewById<LinearLayout>(R.id.listVisitWrapper)?.removeAllViews()
                            listOfVisitActivity.listOfVisitActivityViewModel.onNeedToGetVisitList()
                        } else if ( root!!.findViewById<LinearLayout>(R.id.listVisitWrapper)?.size == 0){
                            listOfVisitActivity.listOfVisitActivityViewModel.onNeedToGetVisitList()
                        }
                    }, { err ->
                        println(err.message)
                    })
                }

        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun getMaintenanceWhenSelectVisit() {
        try {
            if (null != root) {
                Fuel.get(requestHttp.url+"Maintenance/" + VisitSelected.visit.ID_Maintenance)
                    .responseString { _, _, result ->
                        result.fold({ d ->
                            VisitSelected.maintenance = json.parse(Maintenance.serializer(), d)
                            getTechnicalWhenSelectVisit()
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun getTechnicalWhenSelectVisit() {
        try {
            if (null != root) {
                Fuel.get(requestHttp.url+"Technical/" + VisitSelected.visit.ID_Technical)
                    .responseString { _, _, result ->
                        result.fold({ d ->
                            VisitSelected.technical = json.parse(Technical.serializer(), d)
                            getObservationWhenSelectVisit()
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    override fun getObservationWhenSelectVisit() {
        try {
            if (null != root) {
                Fuel.get(requestHttp.url+"Observation/" + VisitSelected.visit.ID_Observation)
                    .responseString { _, _, result ->
                        result.fold({ d ->
                            VisitSelected.observation = json.parse(Observation.serializer(), d)
                            VisitDetailsActivity.start(listOfVisitActivity.layoutInflater.context)
                        }, { err ->
                            println(err.message)
                        })
                    }
            }
        }catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}