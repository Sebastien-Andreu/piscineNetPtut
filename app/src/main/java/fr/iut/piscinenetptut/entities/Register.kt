package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Register (
    val login: String,
    val password: String
)