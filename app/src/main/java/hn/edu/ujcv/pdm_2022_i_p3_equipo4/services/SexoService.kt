package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.SexoDataCollectionItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SexoService {

    @GET("sexo")
    fun listSexo(): Call<List<SexoDataCollectionItem>>

    @GET("sexo/id/{id}")
    fun getSexoById(@Path("id") id:Int): Call<SexoDataCollectionItem>

    @GET("sexo/nombre/{nombre}")
    fun getByNombreSexo(@Path("nombre") nombre:String): Call<SexoDataCollectionItem>


}