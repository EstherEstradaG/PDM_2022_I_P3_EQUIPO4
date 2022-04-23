package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.*
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.*
import kotlinx.android.synthetic.main.activity_pedido.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class Pedido : AppCompatActivity() {
    var repartidorElegido : RepartidorDataCollectionItem? = null
    var detallePedido = arrayListOf<DetallePedidoDataCollectionItem>()
    var articulos : HashMap<Int, ArticulosDataCollectionItem> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        editTextFechaPedido.setText(SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()))
        editTextImpuesto.setText("15%")

        agregar.setOnClickListener{
            agregarDetalle()
        }

        btnGuardarPedido.setOnClickListener {
            callServicePostPedido()
        }


        spinnerTipoPago.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when {
                    spinnerTipoPago.selectedItem.toString().split(" ")[1].lowercase() == "tarjeta" -> {
                        editTextTarjeta.isEnabled = true
                    }
                    spinnerTipoPago.selectedItem.toString().split(" ")[1].lowercase() == "mixto" -> {
                        editTextTarjeta.isEnabled = true
                        editTextMonto.isEnabled = true
                    }
                    else -> {
                        editTextTarjeta.isEnabled = false
                        editTextMonto.isEnabled = false
                    }
                }
            }

        }

        cargarArticulos()
        cargarTipoPago()
        cargarTipoEntrega()
        elegirRepartidor()
    }



    private fun cargarArticulos(){
        val articulosService : ArticulosService = RestEngine.buildService().create(ArticulosService::class.java)
        val result : Call<List<ArticulosDataCollectionItem>> = articulosService.listArticulo()
        result.enqueue(object : Callback<List<ArticulosDataCollectionItem>> {
            override fun onFailure(call: Call<List<ArticulosDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@Pedido, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<ArticulosDataCollectionItem>>,
                response: Response<List<ArticulosDataCollectionItem>>
            ) {
                val arrayArticulos = arrayListOf<String>()
                for (item in response.body()!!){
                    if (item.estadoArticulo){
                        articulos[item.idArticulos!!] = item
                        arrayArticulos.add("${item.idArticulos} ${item.nombreArticulo}")
                    }
                }
                val listAdapter = ArrayAdapter(this@Pedido,android.R.layout.simple_list_item_1, arrayArticulos)
                spinnerArticulos.adapter = listAdapter
            }
        })
    }

    private fun cargarTipoPago(){
        val tipoPagoService : TipoDePagoService = RestEngine.buildService().create(TipoDePagoService::class.java)
        val result : Call<List<TipoDePagoDataCollectionItem>> = tipoPagoService.listTipoDePago()
        result.enqueue(object : Callback<List<TipoDePagoDataCollectionItem>> {
            override fun onFailure(call: Call<List<TipoDePagoDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@Pedido, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<TipoDePagoDataCollectionItem>>,
                response: Response<List<TipoDePagoDataCollectionItem>>
            ) {
                val arrayTipoPago = arrayListOf<String>()
                for (item in response.body()!!){
                    arrayTipoPago.add("${item.idTipoDePago} ${item.nombreTipoDePago}")
                }
                val listAdapter = ArrayAdapter(this@Pedido,android.R.layout.simple_list_item_1, arrayTipoPago)
                spinnerTipoPago.adapter = listAdapter
            }
        })
    }

    private fun cargarTipoEntrega(){
        val tipoEntregaService : TipoDeEntregaService = RestEngine.buildService().create(TipoDeEntregaService::class.java)
        val result : Call<List<TipoDeEntregaDataCollectionItem>> = tipoEntregaService.listTipoDeEntrega()
        result.enqueue(object : Callback<List<TipoDeEntregaDataCollectionItem>> {
            override fun onFailure(call: Call<List<TipoDeEntregaDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@Pedido, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<TipoDeEntregaDataCollectionItem>>,
                response: Response<List<TipoDeEntregaDataCollectionItem>>
            ) {
                val arrayTipoEntrega = arrayListOf<String>()
                for (item in response.body()!!){
                    if (item.estadoTipoDeEntrega)
                    arrayTipoEntrega.add("${item.idTipoDeEntrega} ${item.nombreTipoDeEntrega}")
                }
                val listAdapter = ArrayAdapter(this@Pedido,android.R.layout.simple_list_item_1, arrayTipoEntrega)
                spinnerTipoEntrega.adapter = listAdapter
            }
        })
    }

    private fun elegirRepartidor(){
        val repartidorService : RepartidorService = RestEngine.buildService().create(RepartidorService::class.java)
        val result : Call<List<RepartidorDataCollectionItem>> = repartidorService.listRepartidor()
        result.enqueue(object : Callback<List<RepartidorDataCollectionItem>> {
            override fun onFailure(call: Call<List<RepartidorDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@Pedido, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<RepartidorDataCollectionItem>>,
                response: Response<List<RepartidorDataCollectionItem>>
            ) {
                val arrayRepartidores = arrayListOf<RepartidorDataCollectionItem>()
                for (item in response.body()!!){
                    if (item.estadoRepartidor)
                        arrayRepartidores.add(item)
                }

                if(arrayRepartidores.size == 0){
                    Toast.makeText(this@Pedido,"No hay repartidores disponibles",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Pedido,MenuCliente::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    val rndm = (0..arrayRepartidores.size-1).random()
                    repartidorElegido = arrayRepartidores[rndm]
                    txtRepartidorAsignado.text = "${repartidorElegido!!.nombreRepartidor} ${repartidorElegido!!.apellidoRepartidor} "
                }
            }
        })
    }

    private fun agregarDetalle(){
        //validacion stock
        if (articulos[spinnerArticulos.selectedItem.toString().split(" ")[0].toInt()]!!.stock < editTextCantidad.text.toString().toInt() ){
            Toast.makeText(this,"No se encuentran tantos productos en existencia. Productos restantes:" + articulos[spinnerArticulos.selectedItem.toString().split(" ")[0].toInt()]!!.stock,
                Toast.LENGTH_SHORT).show()
            return
        }

        var detalleAgregar = DetallePedidoDataCollectionItem(idDetallePedido = null,
            precio = articulos[spinnerArticulos.selectedItem.toString().split(" ")[0].toInt()]!!.precioArticulo.toFloat(),
            cantidad = editTextCantidad.text.toString().toInt(),
            idArticulos = spinnerArticulos.selectedItem.toString().split(" ")[0].toInt(),
            idPedido = null
        )
        detallePedido.add(detalleAgregar)
        var detalleString = arrayListOf<String>()
        for (item in detallePedido){
            detalleString.add("${item.cantidad}   ${articulos[item.idArticulos]!!.nombreArticulo}   ${articulos[item.idArticulos]!!.precioArticulo}   ${articulos[item.idArticulos]!!.precioArticulo*item.cantidad}  ")
        }
        var arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,detalleString)
        listDetalles.adapter = arrayAdapter
        subTotalTxt.text = calcularSubtotal().toString()
        totalTxt.text = calcularTotal().toString()
        editTextCantidad.setText("")
    }

    private fun calcularSubtotal() : Double{
        var subtotal = 0.0
        for (item in detallePedido){
            subtotal += item.cantidad * articulos[item.idArticulos]!!.precioArticulo
        }
        return subtotal
    }

    private fun calcularTotal() : Double{
        var total = 0.00
        total = (calcularSubtotal() * 0.15) + calcularSubtotal()
        return total
    }

    private fun callServicePostPedido(){
        val pedido = PedidoDataCollectionItem(idPedido = null,
        fechaPedido = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()),
        impuesto = 0.15f,
        subTotal = subTotalTxt.text.toString().toFloat(),
        total = totalTxt.text.toString().toFloat(),
        direccionPedido = editTextDireccion.text.toString(),
        idCliente = ClienteLogueado.clienteLogueado!!.idCliente!!,
        idRepartidor = repartidorElegido!!.idRepartidor!!,
        idTipoDePago = spinnerTipoPago.selectedItem.toString().split(" ")[0].toInt(),
        idTipoDeEntrega = spinnerTipoEntrega.selectedItem.toString().split(" ")[0].toInt(),
        idEstado = 1)

        //validar tarjeta y monto
        if (spinnerTipoPago.selectedItem.toString().split(" ")[1].lowercase() == "tarjeta"){
            if (editTextTarjeta.text.toString().trimStart().trimEnd() == ""){
                Toast.makeText(this,"Debes especificar un numero de tarjeta.",Toast.LENGTH_SHORT).show()
                return
            }
        }else if (spinnerTipoPago.selectedItem.toString().split(" ")[1].lowercase() == "mixto"){
            if (editTextTarjeta.text.toString().trimStart().trimEnd() == ""){
                Toast.makeText(this,"Debes especificar un numero de tarjeta.",Toast.LENGTH_SHORT).show()
                return
            }else if (editTextMonto.text.toString().trimStart().trimEnd() == "") {
                Toast.makeText(this, "Debes especificar un monto de tarjeta.", Toast.LENGTH_SHORT).show()
                return
            }else if (editTextMonto.text.toString().toDouble() >= calcularTotal()){
                Toast.makeText(this,"El monto debe ser menor al total.",Toast.LENGTH_SHORT).show()
                return
            }
        }

        addPedido(pedido){
            if (it?.idCliente != null){
                Toast.makeText(this,"Pedido registrado con id: "+it?.idPedido,Toast.LENGTH_SHORT).show()

                for (i in 0 until detallePedido.size)
                    detallePedido[i].idPedido = it.idPedido

                addDetallesPedido(detallePedido){

                }

                val intent = Intent(this,MenuCliente::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addPedido(pedidoData: PedidoDataCollectionItem, onResult : (PedidoDataCollectionItem?) -> Unit) {
        val pedidoService: PedidoService =
            RestEngine.buildService().create(PedidoService::class.java)
        val result: Call<PedidoDataCollectionItem> = pedidoService.addPedido(pedidoData)
        result.enqueue(object : Callback<PedidoDataCollectionItem> {
            override fun onFailure(call: Call<PedidoDataCollectionItem>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(
                call: Call<PedidoDataCollectionItem>,
                response: Response<PedidoDataCollectionItem>
            ) {
                if (response.isSuccessful) {
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                } else if (response.code() == 500) {
                    val errorResponse =
                        Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@Pedido, errorResponse.errorDetails, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        this@Pedido, "Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }


    private fun addDetallesPedido(detallesPedidoData : List<DetallePedidoDataCollectionItem>, onResult: (List<DetallePedidoDataCollectionItem>?) -> Unit){
        val pedidoService: DetallePedidoService = RestEngine.buildService().create(DetallePedidoService::class.java)
        val result: Call<List<DetallePedidoDataCollectionItem>> = pedidoService.addDetallesPedido(detallesPedidoData)
        result.enqueue(object : Callback<List<DetallePedidoDataCollectionItem>> {
            override fun onFailure(call: Call<List<DetallePedidoDataCollectionItem>>, t: Throwable) {
                onResult(null)
            }

            override fun onResponse(
                call: Call<List<DetallePedidoDataCollectionItem>>,
                response: Response<List<DetallePedidoDataCollectionItem>>
            ) {
                if (response.isSuccessful) {
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                } else if (response.code() == 500) {
                    val errorResponse =
                        Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@Pedido, errorResponse.errorDetails, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        this@Pedido, "Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }



}