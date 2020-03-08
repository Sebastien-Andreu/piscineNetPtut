package fr.iut.piscinenetptut.ui.workingmethod

import fr.iut.piscinenetptut.entities.Maintenance
import fr.iut.piscinenetptut.entities.Observation
import fr.iut.piscinenetptut.entities.Technical

interface WorkingMethodActivityMvc {

    fun storeMaintenance(maintenance: Maintenance)
    fun storeTechnical(technical: Technical)
    fun storeObservation(observation: Observation)
    fun storeVisit()

    fun saveMaintenance()
    fun saveTechnical()
    fun saveObservation()
    fun saveVisit()

    interface listeners {
        fun onUserWantToStoreVisit()
        fun onUserWantToStoreMaintenance()
        fun onUserWantToStoreTechnical()
        fun onUserWantToStoreObservation()

        fun onUserWantToFinishVisit()

    }
}