package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ArticulosDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface ArticulosService {

    @GET("articulos")
    fun listArticulo(): Call<List<ArticulosDataCollectionItem>>

    @GET("articulos/id/{id}")
    fun getArticulosById(@Path("id") id:Int): Call<ArticulosDataCollectionItem>

    @GET("articulos/nombre/{nombre}")
    fun getByNombreArticulos(@Path("nombre") nombre:String): Call<ArticulosDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("articulos/addArticulo")
    fun addArticulo(@Body articuloData: ArticulosDataCollectionItem): Call<ArticulosDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("articulos")
    fun updateArticulos(@Body articuloData: ArticulosDataCollectionItem): Call<ArticulosDataCollectionItem>


}