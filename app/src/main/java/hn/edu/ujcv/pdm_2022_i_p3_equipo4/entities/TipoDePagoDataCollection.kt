package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class TipoDePagoDataCollection : ArrayList<TipoDePagoDataCollectionItem>()

data class TipoDePagoDataCollectionItem(
    val idTipoDePago:Int?,
    val nombreTipoDePago:String,
    val descripcionTipoDePago:String
)
