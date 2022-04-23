package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.PedidosCanceladosDataCollectionItem
import retrofit2.Call
import retrofit2.http.*
import java.time.LocalDate

interface PedidosCanceladosService {

    @GET("pedidoscancelados")
    fun listPedidosCancelados(): Call<List<PedidosCanceladosDataCollectionItem>>

    @GET("pedidoscancelados/id/{id}")
    fun getPedidosCanceladoById(@Path("id") id:Int): Call<PedidosCanceladosDataCollectionItem>

    @GET("pedidoscancelados/fechaCancelacion/{fechaCancelacion}")
    fun getPedidosCanceladoByFechaCancelacion(@Path("fechaCancelacion") fechaCancelacion: LocalDate): Call<PedidosCanceladosDataCollectionItem>

    @GET("pedidoscancelados/idPedido/{idPedido}")
    fun getPedidosCanceladoByIdPedido(@Path("idPedido") idPedido:Int): Call<PedidosCanceladosDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("pedidoscancelados/addPedidoCancelado")
    fun addPedidosCancelado(@Body pedidosCanceladoData: PedidosCanceladosDataCollectionItem): Call<PedidosCanceladosDataCollectionItem>

    @Headers("Content-Type: application/json")
    @PUT("pedidoscancelados")
    fun updatePedidosCancelado(@Body pedidosCanceladoData: PedidosCanceladosDataCollectionItem): Call<PedidosCanceladosDataCollectionItem>


}