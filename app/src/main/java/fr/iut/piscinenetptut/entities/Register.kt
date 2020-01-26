package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Register (
    var ID: Int? = null,
    var login: String,
    var password: String?= null,
    var mail: String? = null,
    var role: String? = null,
    var ID_Customer: Int? = null
)