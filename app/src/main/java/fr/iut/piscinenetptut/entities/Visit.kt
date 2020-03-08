package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Visit (
    var ID: Int? = null,
    var ID_Maintenance: String?,
    var ID_Technical: String?,
    var ID_Observation: String?,
    var NameEmployee: String?,
    var NameCustomer: String?
)