package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TiempoDeEntregaDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface TiempoDeEntregaService {

    @GET("tiempodeentrega")
    fun listTiempoDeEntrega(): Call<List<TiempoDeEntregaDataCollectionItem>>

    @GET("tiempodeentrega/id/{id}")
    fun getTiempoDeEntregaById(@Path("id") id:Int): Call<TiempoDeEntregaDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("tiempodeentrega/addTiempoDeEntrega")
    fun addTiempoDeEntrega(@Body tiempoDeEntregaData: TiempoDeEntregaDataCollectionItem): Call<TiempoDeEntregaDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("tiempodeentrega")
    fun updateTiempoDeEntregas(@Body tiempoDeEntregaData: TiempoDeEntregaDataCollectionItem): Call<TiempoDeEntregaDataCollectionItem>


}