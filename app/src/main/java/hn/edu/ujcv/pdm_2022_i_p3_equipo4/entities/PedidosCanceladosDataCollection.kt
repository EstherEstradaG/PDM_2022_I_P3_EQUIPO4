package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

import java.time.LocalDate

class PedidosCanceladosDataCollection : ArrayList<PedidosCanceladosDataCollectionItem>()

data class PedidosCanceladosDataCollectionItem(
    val idPedidosCancelados:Int?,
    val fechaCancelacion: LocalDate?,
    val descripcionCancelacion:String,
    val idPedido:Int
)
