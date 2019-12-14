package fr.iut.piscinenetptut.entities

import fr.iut.piscinenetptut.library.extension.createUniqueIdV4

data class User (
    val poolPictureId: Int,
    val surName: String,
    val firstName: String,
    val isWarnings: Boolean,
    val userId: String = createUniqueIdV4()
)