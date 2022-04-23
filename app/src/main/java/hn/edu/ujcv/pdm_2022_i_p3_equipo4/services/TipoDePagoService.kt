package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TipoDePagoDataCollectionItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TipoDePagoService {

    @GET("tipodepago")
    fun listTipoDePago(): Call<List<TipoDePagoDataCollectionItem>>

    @GET("tipodepago/id/{id}")
    fun getTipoDePagoById(@Path("id") id:Int): Call<TipoDePagoDataCollectionItem>

    @GET("tipodepago/nombre/{nombre}")
    fun getByNombreTipoDePago(@Path("nombre") nombre:String): Call<TipoDePagoDataCollectionItem>

}