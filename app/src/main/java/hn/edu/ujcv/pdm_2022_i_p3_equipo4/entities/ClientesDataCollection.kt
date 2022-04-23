package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class ClientesDataCollection : ArrayList<ClientesDataCollectionItem>()

data class ClientesDataCollectionItem(
    val idCliente:Int?,
    val nombreCliente:String,
    val apellidoCliente:String,
    val telefonoCliente:String,
    val direccionCliente:String,
    val correoCliente:String,
    val estadoCliente:Boolean,
    val contrasenaCliente:String,
    val documentoCliente:String,
    val idTipoDeDocumento:Int
)
