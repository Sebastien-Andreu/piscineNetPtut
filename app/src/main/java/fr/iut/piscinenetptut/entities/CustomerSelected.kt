package fr.iut.piscinenetptut.entities

object CustomerSelected {
    var customer = Customer(
        null,null,null,null,null,null,null,
        null,null, null,null,null
    )
    var pool = Pool(
        null,null,null,null,null,null,null,
        null,null, null,null,null, null,
        null,null,null,null,null,null,
        null,null, null,null,null, null
    )

    fun reset(){
        customer = Customer(
            null,null,null,null,null,null,null,
            null,null, null,null,null
        )

        pool = Pool(
            null,null,null,null,null,null,null,
            null,null, null,null,null, null,
            null,null,null,null,null,null,
            null,null, null,null,null, null
        )
    }
}