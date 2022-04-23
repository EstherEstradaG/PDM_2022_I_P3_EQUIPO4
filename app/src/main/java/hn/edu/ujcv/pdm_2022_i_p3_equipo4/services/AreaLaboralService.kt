package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.AreaLaboralDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface AreaLaboralService {

    @GET("arealaboral")
    fun listAreaLaboral(): Call<List<AreaLaboralDataCollectionItem>>

    @GET("arealaboral/id/{id}")
    fun getAreaLaboralById(@Path("id") id:Int): Call<AreaLaboralDataCollectionItem>

    @GET("arealaboral/nombre/{nombre}")
    fun getByNombreAreaLaboral(@Path("nombre") nombre:String): Call<AreaLaboralDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("arealaboral/addAreaLaboral")
    fun addAreaLaboral(@Body areaLaboralData: AreaLaboralDataCollectionItem): Call<AreaLaboralDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("arealaboral")
    fun updateAreaLaboral(@Body areaLaboralData: AreaLaboralDataCollectionItem): Call<AreaLaboralDataCollectionItem>

}