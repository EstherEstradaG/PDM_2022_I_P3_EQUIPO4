package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class DetallePedidoDataCollection : ArrayList<DetallePedidoDataCollectionItem>()

data class DetallePedidoDataCollectionItem(
    val idDetallePedido:Int?,
    val precio:Float,
    val cantidad:Int,
    val idArticulos:Int,
    var idPedido:Int?
)
