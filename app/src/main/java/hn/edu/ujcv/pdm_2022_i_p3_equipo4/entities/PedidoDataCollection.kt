package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

import java.time.LocalDate

class PedidoDataCollection : ArrayList<PedidoDataCollectionItem>()

data class PedidoDataCollectionItem(
    val idPedido:Int?,
    val fechaPedido: String,
    val impuesto:Float,
    val subTotal:Float,
    val total:Float,
    val direccionPedido:String,
    val idCliente:Int,
    val idRepartidor:Int,
    val idTipoDePago:Int,
    val idTipoDeEntrega:Int,
    val idEstado:Int
)
