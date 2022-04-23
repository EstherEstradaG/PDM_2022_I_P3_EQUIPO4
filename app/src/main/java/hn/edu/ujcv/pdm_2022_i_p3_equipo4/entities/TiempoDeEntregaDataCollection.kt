package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class TiempoDeEntregaDataCollection : ArrayList<TiempoDeEntregaDataCollectionItem>()

data class TiempoDeEntregaDataCollectionItem(
    val idTiempoDeEntrega:Int?,
    val estimadoEntrega:String,
    val descripcionEstimadoEntrega:String,
    val estadoTiempoDeEntrega:Boolean
)
