package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Observation (
    var ID: Int? = null,
    var visualObservation: String?,
    var algeaOnWall: String?,
    var brossage: String?,
    var otherObservation: String?
)