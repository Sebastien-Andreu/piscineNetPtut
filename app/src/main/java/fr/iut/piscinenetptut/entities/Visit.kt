package fr.iut.piscinenetptut.entities

import fr.iut.piscinenetptut.library.extension.createUniqueIdV4

data class Visit (
    val surnameCustomer: String,
    val firstnameCustomer: String,
    val isWarnings: Boolean,
    val visitID: String = createUniqueIdV4()
)