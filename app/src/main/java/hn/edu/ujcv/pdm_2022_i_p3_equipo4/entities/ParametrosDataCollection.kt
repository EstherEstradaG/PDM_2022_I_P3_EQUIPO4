package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

import java.time.LocalDate

class ParametrosDataCollection : ArrayList<ParametrosDataCollectionItem>()

data class ParametrosDataCollectionItem(
    val idParametros:Int?,
    val cai:String,
    val fechaEmision: LocalDate?,
    val fechaCaducidad: LocalDate?,
    val facturaInicial:Int,
    val facturaFinal:Int
)
