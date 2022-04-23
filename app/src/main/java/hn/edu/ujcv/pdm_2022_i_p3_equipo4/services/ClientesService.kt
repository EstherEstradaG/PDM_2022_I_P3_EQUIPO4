package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ClientesDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface ClientesService {

    @GET("clientes")
    fun listClientes(): Call<List<ClientesDataCollectionItem>>

    @GET("clientes/id/{id}")
    fun getClienteById(@Path("id") id:Int): Call<ClientesDataCollectionItem>

    @GET("clientes/nombre/{nombre}")
    fun getByNombreCliente(@Path("nombre") nombre:String): Call<ClientesDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("clientes/addCliente")
    fun addCliente(@Body clienteData: ClientesDataCollectionItem): Call<ClientesDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("clientes")
    fun updateCliente(@Body clienteData: ClientesDataCollectionItem): Call<ClientesDataCollectionItem>


}