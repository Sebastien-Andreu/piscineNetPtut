package fr.iut.piscinenetptut.entities

object Account {
    var register : Register = Register(
        ID = null,
        role = null,
        login = null.toString(),
        password = null.toString(),
        mail = null.toString(),
        ID_Customer = null
    )

    fun reset() {
        register = Register(
            ID = null,
            role = null,
            login = null.toString(),
            password = null.toString(),
            mail = null.toString(),
            ID_Customer = null
        )
    }
}