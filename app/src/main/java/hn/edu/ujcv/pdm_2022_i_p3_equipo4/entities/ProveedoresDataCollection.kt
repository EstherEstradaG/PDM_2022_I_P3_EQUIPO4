package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class ProveedoresDataCollection : ArrayList<ProveedoresDataCollectionItem>()

data class ProveedoresDataCollectionItem(
    val idProveedor: Int?,
    val nombreProveedor:String,
    val telefonoProveedor:String,
    val correoProveedor:String,
    val estadoProveedor:Boolean,
    val documentoProveedor:String,
    val idTipoDeDocumento:Int
)
