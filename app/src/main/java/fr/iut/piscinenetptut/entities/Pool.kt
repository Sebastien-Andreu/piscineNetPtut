package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Pool(
    val ID: Int? = null,
    var ID_Customer: Int?,
    val picture: String?,
    val sizeLo: String?,
    val sizeLa: String?,
    val depth: String?,
    val shape: String?,
    val environment: String?,
    val state: String?,
    val cover: String?,
    val distance: String?,
    val warning: String?,
    val acces: String?,
    val winterCover: String?,
    val dateSandFilter: String? = null,
    val brandSandFilter: String? = null,
    val brandFilter: String? = null,
    val cvFilter: String? = null,
    val dateFilter: String? = null,
    val electronicalProduct: String?,
    val dateElectronicalProduct: String?,
    val datePH: String? = null,
    val datePomp: String? = null,
    val dateRemp: String? = null,
    val observation: String? = null
)
