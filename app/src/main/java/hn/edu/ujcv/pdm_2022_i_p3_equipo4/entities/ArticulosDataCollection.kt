package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class ArticulosDataCollection : ArrayList<ArticulosDataCollectionItem>()

data class ArticulosDataCollectionItem(
    val idArticulos:Int?,
    val nombreArticulo:String,
    val stock:Int,
    val descripcionArticulo:String,
    val precioArticulo:Double,
    val estadoArticulo:Boolean,
    val idProveedor:Int,
    val idCategoria:Int,
    val idTiempoDeEntrega:Int
)
