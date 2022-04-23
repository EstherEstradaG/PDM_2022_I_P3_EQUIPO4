package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RepartidorDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface RepartidorService {

    @GET("repartidor")
    fun listRepartidor(): Call<List<RepartidorDataCollectionItem>>

    @GET("repartidor/id/{id}")
    fun getRepartidorById(@Path("id") id:Int): Call<RepartidorDataCollectionItem>

    @GET("repartidor/nombre/{nombre}")
    fun getByNombreRepartidor(@Path("nombre") nombre:String): Call<RepartidorDataCollectionItem>

    @GET("repartidor/documento/{documento}")
    fun getByDocumentoRepartidor(@Path("documento") documento:String): Call<RepartidorDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("repartidor/addRepartidor")
    fun addRepartidores(@Body repartidorData: RepartidorDataCollectionItem): Call<RepartidorDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("repartidor")
    fun updateRepartidor(@Body repartidorData: RepartidorDataCollectionItem): Call<RepartidorDataCollectionItem>


}