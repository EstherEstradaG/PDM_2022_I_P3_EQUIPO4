package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.DetallePedidoDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface DetallePedidoService {

    @GET("detallepedido")
    fun listDetallePedido(): Call<List<DetallePedidoDataCollectionItem>>

    @GET("detallepedido/id/{id}")
    fun getDetallePedidoById(@Path("id") id:Int): Call<DetallePedidoDataCollectionItem>

    @GET("detallepedido/idPedido/{idPedido}")
    fun getDetallePedidoByIdPedido(@Path("idPedido") idPedido:Int): Call<DetallePedidoDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("detallepedido/addDetallePedido")
    fun addDetallePedido(@Body DetallePedidoData: DetallePedidoDataCollectionItem): Call<DetallePedidoDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("detallepedido/addDetallesPedido")
    fun addDetallesPedido(@Body DetallePedidoDataList : List<DetallePedidoDataCollectionItem>): Call<List<DetallePedidoDataCollectionItem>>


}