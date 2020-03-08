package fr.iut.piscinenetptut.entities

import kotlinx.serialization.Serializable

@Serializable
data class Technical (
    var ID: Int? = null,
    var Chlore: String?,
    var PH: String?,
    var stabilisant: String?,
    var TAC: String?,
    var Sel: String?,
    var ProductChlore: String?,
    var ProductPH: String?,
    var ProductGalet: String?,
    var productSel: String?,
    var ProductFloculent: String?,
    var ProductOther: String?
)