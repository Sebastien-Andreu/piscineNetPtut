package fr.iut.piscinenetptut.entities

object VisitSelected {
    var visit = Visit (null,null,null,null,null,null)
    var maintenance = Maintenance ( null,null,null,null,null,null,null,null,null,null,null,null,
        null,null,null,null,null,null, null,null,null,null,null,null,
        null,null,null,null,null,null, null,null,null,null,null,null,
        null,null,null,null,null,null, null,null,null)
    var technical = Technical ( null,null,null,null,null,null,null,null,null,null,null,null)
    var observation = Observation ( null,null,null,null,null)


    fun reset(){
        visit = Visit (null,null,null,null,null,null)
        maintenance = Maintenance ( null,null,null,null,null,null,null,null,null,null,null,null,
            null,null,null,null,null,null, null,null,null,null,null,null,
            null,null,null,null,null,null, null,null,null,null,null,null,
            null,null,null,null,null,null, null,null,null)
        technical = Technical ( null,null,null,null,null,null,null,null,null,null,null,null)
        observation = Observation ( null,null,null,null,null)
    }
}