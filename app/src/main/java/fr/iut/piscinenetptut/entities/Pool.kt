package fr.iut.piscinenetptut.entities

data class Pool(
    val ID: Int?,
    val ID_Customer: Int?,
    val picture: CharSequence?,
    val sizeLo: CharSequence?,
    val sizeLa: CharSequence?,
    val depth: CharSequence?,
    val environment: CharSequence?,
    val state: CharSequence?,
    val cover: CharSequence?,
    val distance: CharSequence?,
    val warning: CharSequence?,
    val acces: CharSequence?,
    val winterCover: CharSequence?,
    val dateSandFilter: CharSequence?,
    val brandSandFilter: CharSequence?,
    val brandFilter: CharSequence?,
    val cvFilter: CharSequence?,
    val dateFilter: CharSequence?,
    val electronicalProduct: CharSequence?,
    val dateElectronicalPoduct: CharSequence?,
    val datePH: CharSequence?,
    val datePomp: CharSequence?,
    val dateRemp: CharSequence?,
    val observation: CharSequence?
)
