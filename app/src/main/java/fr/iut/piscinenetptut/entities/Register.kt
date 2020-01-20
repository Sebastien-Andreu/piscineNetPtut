package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Register (
    var id: Int? = null,
    var role: String? = null,
    val login: String,
    val password: String
)

// il faudra l'id customer