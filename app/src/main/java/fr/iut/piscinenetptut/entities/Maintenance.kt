package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Maintenance (
    var typeOfVisit: String?,
    var analyse: String?,
    var curtain: String,
    var cleaningSkimmer: String?,
    var emptySkimmer: String?,
    var cleaningSkimmerFrame: String?,
    var loopholes: String?,
    var emptyPoolRobotBag: String?,
    var CleaningWaterLine: String?,
    var vacuum: String?,
    var cleaningBeam: String?,
    var cleaningAlarm: String?,
    var properAlarm: String?,
    var passageLandingSurface: String?,
    var passageLandingDepth: String?,
    var goodFloat: String?,
    var floatNotBlocked: String?,

    var bottomDrain: String?,
    var vacuumCleaner: String?,
    var TLFilterWashing: String?,
    var TLCleaningPreFilter: String?,
    var TLChloreLVL: String?,
    var TLPHLVL: String?,
    var TLWaterMeter: String?,
    var TLClockSetting: String?,
    var TLFiltrationMode: String?,
    var TLRobotMode: String?,
    var TLProjectorMode: String?,
    var TLProjectorWater: String?,
    var TLCleaningRoom: String?,
    var PFEDSaltRAS: String?,
    var PFEDSaltCell: String?,
    var PFEDSaltDescalingCell: String?,
    var PFEDSaltOtherProb: String?,
    var PFEDChloraRAS: String?,
    var PFEDChloraFaulty: String?,
    var PFEDPHRAS: String?,
    var PFEDPHFaulty: String?,
    var PFEDUVRAS: String?,
    var PFEDUVCell: String?,
    var PFEDUVOtherProb: String?,
    var PFEDPH2WaterTemp: String?,
    var PFEDPH2RAS: String?,
    var PFEDPH2Faulty: String?
)