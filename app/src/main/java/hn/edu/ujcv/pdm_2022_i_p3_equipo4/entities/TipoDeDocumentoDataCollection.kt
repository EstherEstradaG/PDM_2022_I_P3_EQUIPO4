package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class TipoDeDocumentoDataCollection : ArrayList<TipoDeDocumentoDataCollectionItem>()

data class TipoDeDocumentoDataCollectionItem(
    val idTipoDeDocumento:Int?,
    val nombreTipoDocumento:String,
    val descripcionTipoDocumento:String
)
