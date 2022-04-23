package hn.edu.ujcv.pdm_2022_i_p3_equipo4.services

import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.FacturaDataCollectionItem
import retrofit2.Call
import retrofit2.http.*

interface FacturaService {

    @GET("factura")
    fun listFactura(): Call<List<FacturaDataCollectionItem>>

    @GET("factura/id/{id}")
    fun getFacturaById(@Path("id") id:Int): Call<FacturaDataCollectionItem>

    @Headers("Content-Type: application/json")
    @POST("factura/addFactura")
    fun addFactura(@Body facturaData: FacturaDataCollectionItem): Call<FacturaDataCollectionItem>


}