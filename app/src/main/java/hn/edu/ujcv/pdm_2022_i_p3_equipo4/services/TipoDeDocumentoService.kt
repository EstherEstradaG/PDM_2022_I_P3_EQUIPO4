package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TipoDeDocumentoDataCollectionItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TipoDeDocumentoService {

    @GET("tipodedocumento")
    fun listTipoDeDocumento(): Call<List<TipoDeDocumentoDataCollectionItem>>

    @GET("tipodedocumento/id/{id}")
    fun getTipoDeDocumentoById(@Path("id") id:Int): Call<TipoDeDocumentoDataCollectionItem>

    @GET("tipodedocumento/nombre/{nombre}")
    fun getByNombreTipoDeDocumento(@Path("nombre") nombre:String): Call<TipoDeDocumentoDataCollectionItem>

}