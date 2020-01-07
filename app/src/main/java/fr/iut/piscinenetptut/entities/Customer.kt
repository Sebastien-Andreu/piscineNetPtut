package fr.iut.piscinenetptut.entities

import kotlinx.serialization.*

@Serializable
data class Customer (
    val name: String?,
    val surname: String?,
    val mail: String?,
    val town: String?,
    val postalCode: Int?,
    val telPhoneNumber: Double?,
    val telFixNumber: Double?,
    val typeOfContract: String?,
    val contractOfProduct: String?,
    val guardianNumber: Double?
)