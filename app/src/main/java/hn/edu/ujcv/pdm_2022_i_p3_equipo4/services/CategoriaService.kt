package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.CategoriaDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface CategoriaService {

    @GET("categoria")
    fun listCategoria(): Call<List<CategoriaDataCollectionItem>>

    @GET("categoria/id/{id}")
    fun getCategoriaById(@Path("id") id:Int): Call<CategoriaDataCollectionItem>

    @GET("categoria/nombre/{nombre}")
    fun getByNombreCategoria(@Path("nombre") nombre:String): Call<CategoriaDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("categoria/addCategoria")
    fun addCategoria(@Body categoriaData: CategoriaDataCollectionItem): Call<CategoriaDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("categoria")
    fun updateCategoria(@Body categoriaData: CategoriaDataCollectionItem): Call<CategoriaDataCollectionItem>


}