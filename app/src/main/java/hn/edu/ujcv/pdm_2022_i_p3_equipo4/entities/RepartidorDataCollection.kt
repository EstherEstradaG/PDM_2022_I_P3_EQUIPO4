package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class RepartidorDataCollection : ArrayList<RepartidorDataCollectionItem>()

data class RepartidorDataCollectionItem(
    val idRepartidor:Int?,
    val nombreRepartidor:String,
    val apellidoRepartidor:String,
    val telefonoRepartidor:String,
    val correoRepartidor:String,
    val estadoRepartidor:Boolean,
    val documentoRepartidor:String,
    val idSexo:Int,
    val idTipoDeDocumento:Int
)
