package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

import java.time.LocalDate

class UsuariosDataCollection : ArrayList<UsuariosDataCollectionItem>()

data class UsuariosDataCollectionItem(
    val idUsuario:Int?,
    val nombreEmpleado:String,
    val apellidoEmpleado:String,
    val nombreUsuario:String,
    val contrasena:String,
    val numeroDeIntentos:Int,
    val estadoUsuario:Boolean,
    val admin:Boolean,
    val documentoEmpleado:String,
    val idSexo:Int,
    val idTipoDeDocumento:Int,
    val FechaDeCambio: LocalDate?,
    val idAreaLaboral:Int
)
