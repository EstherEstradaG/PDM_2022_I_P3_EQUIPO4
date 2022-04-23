package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ProveedoresDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface ProveedoresService {

    @GET("proveedores")
    fun listProveedores(): Call<List<ProveedoresDataCollectionItem>>

    @GET("proveedores/id/{id}")
    fun getProveedorById(@Path("id") id:Int): Call<ProveedoresDataCollectionItem>

    @GET("proveedores/nombre/{nombre}")
    fun getByNombreProveedor(@Path("nombre") nombre:String): Call<ProveedoresDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("proveedores/addProveedor")
    fun addProveedor(@Body proveedorData: ProveedoresDataCollectionItem): Call<ProveedoresDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("proveedores")
    fun updateProveedor(@Body proveedorData: ProveedoresDataCollectionItem): Call<ProveedoresDataCollectionItem>


}