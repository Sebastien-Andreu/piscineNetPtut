package fr.iut.piscinenetptut.ui.visitDetails.maintenanceSheet

import android.view.View
import android.widget.*
import androidx.core.view.forEach
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Maintenance
import fr.iut.piscinenetptut.entities.VisitSelected
import fr.iut.piscinenetptut.library.extension.toTreatFor
import java.lang.Exception

class MaintenanceSheetFragmentViewModel {
    private val TAG: String = "MaintenanceSheetFragmentViewModel"


    fun showDetailMaintenance(root: View){
        try {
            val maintenance = VisitSelected.maintenance
            disableAll(root)

            root.findViewById<CheckBox>(R.id.CPAnalyse)?.isChecked = maintenance.analyse.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPCurtain)?.isChecked = maintenance.curtain.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPCleaning)?.isChecked = maintenance.cleaningSkimmer.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPEmptySkimmer)?.isChecked = maintenance.emptySkimmer.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPCleanedSkimmer)?.isChecked = maintenance.cleaningSkimmerFrame.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPLoopholes)?.isChecked = maintenance.loopholes.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPEmptyRobotBag)?.isChecked = maintenance.emptyPoolRobotBag.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPEmptyWaterLine)?.isChecked = maintenance.CleaningWaterLine.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPVaccum)?.isChecked = maintenance.vacuum.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPCleanningBeam)?.isChecked = maintenance.cleaningBeam.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPCleanningAlarm)?.isChecked = maintenance.cleaningAlarm.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPProper)?.isChecked = maintenance.properAlarm.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPPassageLandingSurface)?.isChecked = maintenance.passageLandingSurface.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPPassageLandingDepth)?.isChecked = maintenance.passageLandingDepth.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPGoodFloat)?.isChecked = maintenance.goodFloat.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.CPFloatNotBlocked)?.isChecked = maintenance.floatNotBlocked.toString().toBoolean()

            root.findViewById<CheckBox>(R.id.PFEDRas)?.isChecked = maintenance.PFEDSaltRAS.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDCellProblem)?.isChecked = maintenance.PFEDSaltCell.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDDescaling)?.isChecked = maintenance.PFEDSaltDescalingCell.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDChloraRas)?.isChecked = maintenance.PFEDChloraRAS.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDChloraFaulty)?.isChecked = maintenance.PFEDChloraFaulty.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDPHRas)?.isChecked = maintenance.PFEDPHRAS.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDPHFaulty)?.isChecked = maintenance.PFEDPHFaulty.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDUVRas)?.isChecked = maintenance.PFEDUVRAS.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDUVCell)?.isChecked = maintenance.PFEDUVCell.toString().toBoolean()

            root.findViewById<CheckBox>(R.id.TLCleaningRoom)?.isChecked = maintenance.TLCleaningRoom.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDPH2Ras)?.isChecked = maintenance.PFEDPH2RAS.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.PFEDPH2Faulty)?.isChecked = maintenance.PFEDPH2Faulty.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.TLClockSetting)?.isChecked = maintenance.TLClockSetting.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.TLFilterWashing)?.isChecked = maintenance.TLFilterWashing.toString().toBoolean()
            root.findViewById<CheckBox>(R.id.TLCleanningPreFilter)?.isChecked = maintenance.TLCleaningPreFilter.toString().toBoolean()


            root.findViewById<RadioGroup>(R.id.workingTypeVisit)?.forEach {
                if ((it as RadioButton).text == maintenance.typeOfVisit){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }
            root.findViewById<RadioGroup>(R.id.CPBottomDrain)?.forEach {
                if ((it as RadioButton).text == maintenance.bottomDrain){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }
            root.findViewById<RadioGroup>(R.id.CPVacuumCleaner)?.forEach {
                if ((it as RadioButton).text == maintenance.vacuumCleaner){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }
            root.findViewById<RadioGroup>(R.id.TLFiltrationMode)?.forEach {
                if ((it as RadioButton).text == maintenance.TLFiltrationMode){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }
            root.findViewById<RadioGroup>(R.id.TLRobotMode)?.forEach {
                if ((it as RadioButton).text == maintenance.TLRobotMode){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }
            root.findViewById<RadioGroup>(R.id.TLProjectorMode)?.forEach {
                if ((it as RadioButton).text == maintenance.TLProjectorMode){
                    root.findViewById<RadioButton>(it.id).isChecked = true
                }
            }

            if (maintenance.TLChloreLVL != null){
                root.findViewById<EditText>(R.id.TLChlorine)?.setText(maintenance.TLChloreLVL.toString())
            }
            if (maintenance.TLPHLVL != null){
                root.findViewById<EditText>(R.id.TLPH)?.setText(maintenance.TLPHLVL.toString())
            }
            if (maintenance.TLWaterMeter != null){
                root.findViewById<EditText>(R.id.TLWaterMeter)?.setText(maintenance.TLWaterMeter.toString())
            }
            if (maintenance.PFEDSaltOtherProb != null){
                root.findViewById<EditText>(R.id.PFEDOtherObservation)?.setText(maintenance.PFEDSaltOtherProb.toString())
            }
            if (maintenance.TLProjectorWater != null){
                root.findViewById<EditText>(R.id.TLProjectorMeterReading)?.setText(maintenance.TLProjectorWater.toString())
            }
            if (maintenance.PFEDUVOtherProb != null){
                root.findViewById<EditText>(R.id.PFEDUVOtherProblem)?.setText(maintenance.PFEDUVOtherProb.toString())
            }
            if (maintenance.PFEDPH2WaterTemp != null){
                root.findViewById<EditText>(R.id.PFEDPHWatherTemp)?.setText(maintenance.PFEDPH2WaterTemp.toString())
            }

        } catch (exception: Exception){
            exception.toTreatFor(TAG)
        }
    }

    private fun disableAll(root: View){
        root.findViewById<CheckBox>(R.id.CPAnalyse)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPCurtain)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPCleaning)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPEmptySkimmer)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPCleanedSkimmer)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPLoopholes)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPEmptyRobotBag)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPEmptyWaterLine)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPVaccum)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPCleanningBeam)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPCleanningAlarm)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPProper)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPPassageLandingSurface)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPPassageLandingDepth)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPGoodFloat)?.isClickable = false
        root.findViewById<CheckBox>(R.id.CPFloatNotBlocked)?.isClickable = false

        root.findViewById<CheckBox>(R.id.PFEDRas)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDCellProblem)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDDescaling)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDChloraRas)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDChloraFaulty)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDPHRas)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDPHFaulty)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDUVRas)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDUVCell)?.isClickable = false

        root.findViewById<CheckBox>(R.id.TLCleaningRoom)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDPH2Ras)?.isClickable = false
        root.findViewById<CheckBox>(R.id.PFEDPH2Faulty)?.isClickable = false
        root.findViewById<CheckBox>(R.id.TLClockSetting)?.isClickable = false
        root.findViewById<CheckBox>(R.id.TLFilterWashing)?.isClickable = false
        root.findViewById<CheckBox>(R.id.TLCleanningPreFilter)?.isClickable = false

        root.findViewById<RadioGroup>(R.id.workingTypeVisit)?.forEach {
            root.findViewById<RadioButton>(it.id).isClickable = false
        }
        root.findViewById<RadioGroup>(R.id.CPBottomDrain)?.forEach {
            root.findViewById<RadioButton>(it.id).isClickable = false
        }
        root.findViewById<RadioGroup>(R.id.CPVacuumCleaner)?.forEach {
            root.findViewById<RadioButton>(it.id).isClickable = false
        }
        root.findViewById<RadioGroup>(R.id.TLFiltrationMode)?.forEach {
            root.findViewById<RadioButton>(it.id).isClickable = false
        }
        root.findViewById<RadioGroup>(R.id.TLRobotMode)?.forEach {
            root.findViewById<RadioButton>(it.id).isClickable = false
        }
        root.findViewById<RadioGroup>(R.id.TLProjectorMode)?.forEach {
            root.findViewById<RadioButton>(it.id).isClickable = false
        }

        root.findViewById<EditText>(R.id.TLChlorine)?.inputType = 0
        root.findViewById<EditText>(R.id.TLPH)?.inputType = 0
        root.findViewById<EditText>(R.id.TLWaterMeter)?.inputType = 0
        root.findViewById<EditText>(R.id.PFEDOtherObservation)?.inputType = 0
        root.findViewById<EditText>(R.id.TLProjectorMeterReading)?.inputType = 0
        root.findViewById<EditText>(R.id.PFEDUVOtherProblem)?.inputType = 0
        root.findViewById<EditText>(R.id.PFEDPHWatherTemp)?.inputType = 0

    }
}