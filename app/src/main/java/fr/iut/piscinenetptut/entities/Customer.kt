package fr.iut.piscinenetptut.entities

data class Customer(
    val ID: Int?,
    val name: CharSequence?,
    val surname: CharSequence?,
    val mail: CharSequence?,
    val town: CharSequence?,
    val postalCode: CharSequence?,
    val telPhoneNumber: CharSequence?,
    val telFixNumber: CharSequence?,
    val typeOfContract: CharSequence?,
    val contractOfProduct: CharSequence?,
    val guardianNumber: CharSequence?
)