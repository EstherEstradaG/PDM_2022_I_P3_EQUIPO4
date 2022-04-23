package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TipoDeEntregaDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface TipoDeEntregaService {

    @GET("tipodeentrega")
    fun listTipoDeEntrega(): Call<List<TipoDeEntregaDataCollectionItem>>

    @GET("tipodeentrega/id/{id}")
    fun getTipoDeEntregaById(@Path("id") id:Int): Call<TipoDeEntregaDataCollectionItem>

    @GET("tipodeentrega/nombre/{nombre}")
    fun getByNombreTipoDeEntrega(@Path("nombre") nombre:String): Call<TipoDeEntregaDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("tipodeentrega/addTipoDeEntrega")
    fun addTipoDeEntrega(@Body tipoDeEntregaData: TipoDeEntregaDataCollectionItem): Call<TipoDeEntregaDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("tipodeentrega")
    fun updateTipoDeEntrega(@Body tipoDeEntregaData: TipoDeEntregaDataCollectionItem): Call<TipoDeEntregaDataCollectionItem>


}