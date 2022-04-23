package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ParametrosDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface ParametrosService {

    @GET("parametros")
    fun listParametros(): Call<List<ParametrosDataCollectionItem>>

    @GET("parametros/id/{id}")
    fun getParametroById(@Path("id") id:Int): Call<ParametrosDataCollectionItem>

    @GET("parametros/nombre/{nombre}")
    fun getByNombreParametro(@Path("nombre") nombre:String): Call<ParametrosDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("parametros/addParametro")
    fun addParametro(@Body parametroData: ParametrosDataCollectionItem): Call<ParametrosDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("parametros")
    fun updateParametro(@Body parametroData: ParametrosDataCollectionItem): Call<ParametrosDataCollectionItem>


}