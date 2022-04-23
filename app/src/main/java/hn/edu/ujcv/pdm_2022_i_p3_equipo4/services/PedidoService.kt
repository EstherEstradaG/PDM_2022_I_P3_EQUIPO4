package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.PedidoDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface PedidoService {

    @GET("pedido")
    fun listPedido(): Call<List<PedidoDataCollectionItem>>

    @GET("pedido/id/{id}")
    fun getPedidoById(@Path("id") id:Int): Call<PedidoDataCollectionItem>

    @GET("pedido/idCliente/{idCliente}")
    fun getPedidoByIdCliente(@Path("idCliente") idCliente:Int): Call<PedidoDataCollectionItem>

    @GET("pedido/idRepartidor/{idRepartidor}")
    fun getPedidoByIdRepartidor(@Path("idRepartidor") idRepartidor:Int): Call<PedidoDataCollectionItem>

    @GET("pedido/idEstado/{idEstado}")
    fun getPedidoByIdEstado(@Path("idEstado") idEstado:Int): Call<PedidoDataCollectionItem>

    @GET("pedido/idTipoDeEntrega/{idTipoDeEntrega}")
    fun getPedidoByIdTipoDeEntrega(@Path("idTipoDeEntrega") idTipoDeEntrega:Int): Call<PedidoDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("pedido/addPedido")
    fun addPedido(@Body pedidoData: PedidoDataCollectionItem): Call<PedidoDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("pedido")
    fun updatePedido(@Body pedidoData: PedidoDataCollectionItem): Call<PedidoDataCollectionItem>


}