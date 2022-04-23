package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class TipoDeEntregaDataCollection : ArrayList<TipoDeEntregaDataCollectionItem>()

data class TipoDeEntregaDataCollectionItem(
    val idTipoDeEntrega:Int?,
    val nombreTipoDeEntrega:String,
    val descripcionTipoDeEntrega:String,
    val estadoTipoDeEntrega:Boolean
)
