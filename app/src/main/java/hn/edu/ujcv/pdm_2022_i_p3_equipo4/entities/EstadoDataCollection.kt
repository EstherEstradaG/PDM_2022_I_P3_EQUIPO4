package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class EstadoDataCollection : ArrayList<EstadoDataCollectionItem>()

data class EstadoDataCollectionItem(
    val idEstado:Int?,
    val nombreEstado:String,
    val descripcionEstado:String,
    val activoEstado:Boolean
)
