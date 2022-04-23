package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class CategoriaDataCollection : ArrayList<CategoriaDataCollectionItem>()

data class CategoriaDataCollectionItem(
    val idCategoria:Int?,
    val nombreCategoria:String,
    val descripcionCategoria:String,
    val estadoCategoria:Boolean
)
