package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Observation (
    var visualObservation: String?,
    var algeaOnWall: String?,
    var brossagte: String?,
    var otherObservation: String?
)