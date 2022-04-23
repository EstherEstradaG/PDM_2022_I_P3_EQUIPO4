package hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities

class AreaLaboralDataCollection : ArrayList<AreaLaboralDataCollectionItem>()

data class AreaLaboralDataCollectionItem(
    val idAreaLaboral:Int?,
    val nombreAreaLaboral:String,
    val descripcionAreaLaboral:String,
    val estadoAreaLaboral:Boolean
)
