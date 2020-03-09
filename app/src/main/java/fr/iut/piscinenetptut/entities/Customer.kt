package fr.iut.piscinenetptut.entities

import kotlinx.serialization.*

@Serializable
data class Customer (
    var ID: Int? = null,
    val name: String?,
    val surname: String?,
    val mail: String?,
    val address: String?,
    val town: String?,
    val postalCode: Int?,
    val telPhoneNumber: String?,
    val telFixNumber: String?,
    val typeOfContract: String?,
    val contractOfProduct: String?,
    val guardianNumber: String?
)