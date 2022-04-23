package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

import java.time.LocalDate

class FacturaDataCollection : ArrayList<FacturaDataCollectionItem>()

data class FacturaDataCollectionItem(
    val idFactura:Int?,
    val fechaFactura: LocalDate?,
    val numeroFactura:String,
    val totalFactura:Float,
    val idPedido:Int,
    val idParametros:Int
)
