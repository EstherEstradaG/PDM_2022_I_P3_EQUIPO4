package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.UsuariosDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface UsuariosService {

    @GET("usuarios")
    fun listUsuarios(): Call<List<UsuariosDataCollectionItem>>

    @GET("usuarios/id/{id}")
    fun getUsuarioById(@Path("id") id:Int): Call<UsuariosDataCollectionItem>

    @GET("usuarios/nombreUsuario/{nombreUsuario}")
    fun getByNombreUsuario(@Path("nombreUsuario") nombreUsuario:String): Call<UsuariosDataCollectionItem>

    @GET("usuarios/documento/{documento}")
    fun getByDocumentoUsuario(@Path("documento") documento:String): Call<UsuariosDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("usuarios/addUsuario")
    fun addUsuarios(@Body usuarioData: UsuariosDataCollectionItem): Call<UsuariosDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("usuarios")
    fun updateUsuario(@Body usuarioData: UsuariosDataCollectionItem): Call<UsuariosDataCollectionItem>


}