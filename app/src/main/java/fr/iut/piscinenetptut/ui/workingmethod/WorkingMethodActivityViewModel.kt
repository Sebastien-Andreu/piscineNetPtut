package fr.iut.piscinenetptut.ui.workingmethod

import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.MutableLiveData
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Maintenance
import fr.iut.piscinenetptut.entities.Observation
import fr.iut.piscinenetptut.entities.Technical
import fr.iut.piscinenetptut.library.extension.toTreatFor

class WorkingMethodActivityViewModel {

    val TAG: String = "WorkingMethodActivityViewModel"

    val maintenanceCallBack: MutableLiveData<Maintenance> = MutableLiveData()
    val technicalCallBack: MutableLiveData<Technical> = MutableLiveData()
    val observationCallBack: MutableLiveData<Observation> = MutableLiveData()


    fun onNeedToGetMaintenanceInformation(root: View) {
        try {
            val idTypeVisit = root.findViewById<RadioGroup>(R.id.workingTypeVisit)?.checkedRadioButtonId
            val idBottomDrain = root.findViewById<RadioGroup>(R.id.CPBottomDrain)?.checkedRadioButtonId
            val idCavuumCleaner  = root.findViewById<RadioGroup>(R.id.CPVacuumCleaner)?.checkedRadioButtonId
            val idTLFiltrationMode = root.findViewById<RadioGroup>(R.id.TLFiltrationMode)?.checkedRadioButtonId
            val idTLRobotMode = root.findViewById<RadioGroup>(R.id.TLRobotMode)?.checkedRadioButtonId
            val idTLProjectorMode  = root.findViewById<RadioGroup>(R.id.TLProjectorMode)?.checkedRadioButtonId

            val maintenance = Maintenance (
                typeOfVisit = root.findViewById<RadioButton>(idTypeVisit!!)?.text.toString(),
                analyse = root.findViewById<CheckBox>(R.id.CPAnalyse)?.isChecked.toString(),
                curtain = root.findViewById<CheckBox>(R.id.CPCurtain)?.isChecked.toString(),
                cleaningSkimmer = root.findViewById<CheckBox>(R.id.CPCleaning)?.isChecked.toString(),
                emptySkimmer = root.findViewById<CheckBox>(R.id.CPEmptySkimmer)?.isChecked.toString(),
                cleaningSkimmerFrame = root.findViewById<CheckBox>(R.id.CPCleanedSkimmer)?.isChecked.toString(),
                loopholes = root.findViewById<CheckBox>(R.id.CPLoopholes)?.isChecked.toString(),
                emptyPoolRobotBag = root.findViewById<CheckBox>(R.id.CPEmptyRobotBag)?.isChecked.toString(),
                CleaningWaterLine = root.findViewById<CheckBox>(R.id.CPEmptyWaterLine)?.isChecked.toString(),
                vacuum = root.findViewById<CheckBox>(R.id.CPVaccum)?.isChecked.toString(),
                cleaningBeam = root.findViewById<CheckBox>(R.id.CPCleanningBeam)?.isChecked.toString(),
                cleaningAlarm = root.findViewById<CheckBox>(R.id.CPCleanningAlarm)?.isChecked.toString(),
                properAlarm = root.findViewById<CheckBox>(R.id.CPProper)?.isChecked.toString(),
                passageLandingSurface = root.findViewById<CheckBox>(R.id.CPPassageLandingSurface)?.isChecked.toString(),
                passageLandingDepth = root.findViewById<CheckBox>(R.id.CPPassageLandingDepth)?.isChecked.toString(),
                goodFloat = root.findViewById<CheckBox>(R.id.CPGoodFloat)?.isChecked.toString(),
                floatNotBlocked = root.findViewById<CheckBox>(R.id.CPFloatNotBlocked)?.isChecked.toString(),

                bottomDrain = root.findViewById<RadioButton>(idBottomDrain!!)?.text.toString(),
                vacuumCleaner = root.findViewById<RadioButton>(idCavuumCleaner!!)?.text.toString(),

                TLFilterWashing = root.findViewById<CheckBox>(R.id.TLFilterWashing)?.isChecked.toString(),
                TLCleaningPreFilter = root.findViewById<CheckBox>(R.id.TLCleanningPreFilter)?.isChecked.toString(),

                TLChloreLVL = root.findViewById<EditText>(R.id.TLChlorine)?.text.toString(),
                TLPHLVL = root.findViewById<EditText>(R.id.TLPH)?.text.toString(),
                TLWaterMeter = root.findViewById<EditText>(R.id.TLWaterMeter)?.text.toString(),

                TLClockSetting = root.findViewById<CheckBox>(R.id.TLClockSetting)?.isChecked.toString(),

                TLFiltrationMode = root.findViewById<RadioButton>(idTLFiltrationMode!!)?.text.toString(),
                TLRobotMode = root.findViewById<RadioButton>(idTLRobotMode!!)?.text.toString(),
                TLProjectorMode = root.findViewById<RadioButton>(idTLProjectorMode!!)?.text.toString(),

                TLProjectorWater = root.findViewById<EditText>(R.id.TLProjectorMeterReading)?.text.toString(),

                TLCleaningRoom = root.findViewById<CheckBox>(R.id.TLCleaningRoom)?.isChecked.toString(),

                PFEDSaltRAS = root.findViewById<CheckBox>(R.id.PFEDRas)?.isChecked.toString(),
                PFEDSaltCell = root.findViewById<CheckBox>(R.id.PFEDCellProblem)?.isChecked.toString(),
                PFEDSaltDescalingCell = root.findViewById<CheckBox>(R.id.PFEDDescaling)?.isChecked.toString(),
                PFEDSaltOtherProb = root.findViewById<EditText>(R.id.PFEDOtherObservation)?.text.toString(),
                PFEDChloraRAS = root.findViewById<CheckBox>(R.id.PFEDChloraRas)?.isChecked.toString(),
                PFEDChloraFaulty = root.findViewById<CheckBox>(R.id.PFEDChloraFaulty)?.isChecked.toString(),
                PFEDPHRAS = root.findViewById<CheckBox>(R.id.PFEDPHRas)?.isChecked.toString(),
                PFEDPHFaulty = root.findViewById<CheckBox>(R.id.PFEDPHFaulty)?.isChecked.toString(),
                PFEDUVRAS = root.findViewById<CheckBox>(R.id.PFEDUVRas)?.isChecked.toString(),
                PFEDUVCell = root.findViewById<CheckBox>(R.id.PFEDUVCell)?.isChecked.toString(),
                PFEDUVOtherProb = root.findViewById<EditText>(R.id.PFEDUVOtherProblem)?.text.toString(),
                PFEDPH2WaterTemp = root.findViewById<EditText>(R.id.PFEDPHWatherTemp)?.text.toString(),
                PFEDPH2RAS = root.findViewById<CheckBox>(R.id.PFEDPH2Ras)?.isChecked.toString(),
                PFEDPH2Faulty = root.findViewById<CheckBox>(R.id.PFEDPH2Faulty)?.isChecked.toString()
            )
            maintenanceCallBack.postValue(maintenance)
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    fun onNeedToGetTechnicalInformation(root: View) {
        try {
            val technical = Technical (
                 Chlore = root.findViewById<EditText>(R.id.AnalyseChlore)?.text.toString(),
                 PH =root.findViewById<EditText>(R.id.AnalysePh)?.text.toString(),
                 stabilisant = root.findViewById<EditText>(R.id.AnalyseStab)?.text.toString(),
                 TAC = root.findViewById<EditText>(R.id.AnalyseTAC)?.text.toString(),
                 Sel = root.findViewById<EditText>(R.id.AnalyseSel)?.text.toString(),
                 ProductChlore = root.findViewById<EditText>(R.id.ProductChlore)?.text.toString(),
                 ProductPH = root.findViewById<EditText>(R.id.ProductPH)?.text.toString(),
                 ProductGalet = root.findViewById<EditText>(R.id.ProductGalet)?.text.toString(),
                 productSel = root.findViewById<EditText>(R.id.ProductSel)?.text.toString(),
                 ProductFloculent = root.findViewById<EditText>(R.id.ProductFloc)?.text.toString(),
                 ProductOther = root.findViewById<EditText>(R.id.ProductOther)?.text.toString()
            )
            technicalCallBack.postValue(technical)
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    fun onNeedToGetObservationInformation(root: View) {
        try {

            val idObservationVisual = root.findViewById<RadioGroup>(R.id.ObservationVisual)?.checkedRadioButtonId
            val idObservationAlgae = root.findViewById<RadioGroup>(R.id.ObservationWalls)?.checkedRadioButtonId
            val idObservationBrossage  = root.findViewById<RadioGroup>(R.id.ObservationBrossage)?.checkedRadioButtonId

            val observation = Observation (
                visualObservation = root.findViewById<RadioButton>(idObservationVisual!!)?.text.toString(),
                algeaOnWall = root.findViewById<RadioButton>(idObservationAlgae!!)?.text.toString(),
                brossage = root.findViewById<RadioButton>(idObservationBrossage!!)?.text.toString(),
                otherObservation = root.findViewById<EditText>(R.id.ObservationProblem)?.text.toString()
            )
            observationCallBack.postValue(observation)
        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }
}