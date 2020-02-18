package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Employee (
    val ID: Int? = null,
    val name: String?,
    val surname: String?,
    val mail: String?,
    val address: String?,
    val telPhoneNumber: String?,
    val picture: String? = null
)