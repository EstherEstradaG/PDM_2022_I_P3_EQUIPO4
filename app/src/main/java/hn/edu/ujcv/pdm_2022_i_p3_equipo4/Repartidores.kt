package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RepartidorDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TipoDeDocumentoDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.UsuariosDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RepartidorService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.TipoDeDocumentoService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.UsuariosService
import kotlinx.android.synthetic.main.activity_proveedores.*
import kotlinx.android.synthetic.main.activity_registrar.*
import kotlinx.android.synthetic.main.activity_repartidores.*
import kotlinx.android.synthetic.main.activity_usuarios.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repartidores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repartidores)


        btnMostrarRepartidor.setOnClickListener{
            val intent: Intent = Intent(this, MostrarRepartidores::class.java)
            startActivity(intent)
        }

        btnGuardarRepartidores.setOnClickListener{
            callServiceRepartidor()
        }

        cargarTiposDocumento()

    }


    private fun callServiceRepartidor(){
        val repartidores = RepartidorDataCollectionItem(idRepartidor = null,
            nombreRepartidor = txtNombreUsuarios.text.toString(),
            apellidoRepartidor = txtApellidoCliente3.text.toString(),
            telefonoRepartidor = txtNombreUsuario.text.toString(),
            correoRepartidor = txtContraseñaUsuario.text.toString(),
            estadoRepartidor = true,
            documentoRepartidor = txtDocumentoRepartidor.text.toString(),
            idSexo = spnSexoRepartidor.selectedItem.toString().split(" ")[0].toInt(),
            idTipoDeDocumento = spnTipoDocumentoRepartidor.selectedItem.toString().split(" ")[0].toInt()

        )


        addRepartidores(repartidores){
            if (it?.idRepartidor != null){
                Toast.makeText(this,"Te has registrado: "+it?.idRepartidor, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addRepartidores(RepartidoresData: RepartidorDataCollectionItem, onResult : (RepartidorDataCollectionItem?) -> Unit){
        val RepartidorService : RepartidorService = RestEngine.buildService().create(
            RepartidorService::class.java)
        val result : Call<RepartidorDataCollectionItem> = RepartidorService.addRepartidores(RepartidoresData)
        result.enqueue(object : Callback<RepartidorDataCollectionItem> {
            override fun onFailure(call: Call<RepartidorDataCollectionItem>, t: Throwable) {
                onResult(null)
            }
            override fun onResponse(
                call: Call<RepartidorDataCollectionItem>,
                response: Response<RepartidorDataCollectionItem>
            ) {
                if (response.isSuccessful){
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                } else if (response.code() == 500){
                    val errorResponse = Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@Repartidores,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@Repartidores,"Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    private fun cargarTiposDocumento(){
        val usuarioService : TipoDeDocumentoService = RestEngine.buildService().create(
            TipoDeDocumentoService::class.java)
        val result : Call<List<TipoDeDocumentoDataCollectionItem>> = usuarioService.listTipoDeDocumento()
        result.enqueue(object : Callback<List<TipoDeDocumentoDataCollectionItem>> {
            override fun onFailure(call: Call<List<TipoDeDocumentoDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@Repartidores, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<TipoDeDocumentoDataCollectionItem>>,
                response: Response<List<TipoDeDocumentoDataCollectionItem>>
            ) {
                val arrayTipoDoc = arrayListOf<String>()
                for (item in response.body()!!){
                    arrayTipoDoc.add("${item.idTipoDeDocumento} ${item.nombreTipoDocumento}")
                }
                val listAdapter = ArrayAdapter(this@Repartidores,android.R.layout.simple_list_item_1, arrayTipoDoc)
                spinnerTiposDoc.adapter = listAdapter
            }
        })
    }



}