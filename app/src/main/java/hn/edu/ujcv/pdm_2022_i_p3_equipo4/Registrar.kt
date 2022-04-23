package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ClientesDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TipoDeDocumentoDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ClientesService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.TipoDeDocumentoService
import kotlinx.android.synthetic.main.activity_registrar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registrar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        cargarTiposDocumento()

        btnGuardarCliente.setOnClickListener {
            callServicePostCliente()
        }
    }



    private fun callServicePostCliente(){
        val cliente = ClientesDataCollectionItem(idCliente = null,
            nombreCliente = txtNombreCliente.text.toString(),
            apellidoCliente = txtApellidoCliente.text.toString(),
            telefonoCliente = txtTelefonoCliente.text.toString(),
            direccionCliente = txtDireccionCliente2.text.toString(),
            correoCliente = txtCorreoCliente.text.toString(),
            estadoCliente = true,
            contrasenaCliente = txtContrasenaCliente.text.toString(),
            documentoCliente = txtDocumentoCliente.text.toString(),
            idTipoDeDocumento = spinnerTiposDoc.selectedItem.toString().split(" ")[0].toInt()
        )
        addCliente(cliente){
            if (it?.idCliente != null){
                Toast.makeText(this,"Te has registrado con Id: "+it?.idCliente,Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, LoginCliente::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addCliente(clienteData: ClientesDataCollectionItem, onResult : (ClientesDataCollectionItem?) -> Unit){
        val clientesService : ClientesService = RestEngine.buildService().create(ClientesService::class.java)
        val result : Call<ClientesDataCollectionItem> = clientesService.addCliente(clienteData)
        result.enqueue(object : Callback<ClientesDataCollectionItem> {
            override fun onFailure(call: Call<ClientesDataCollectionItem>, t: Throwable) {
            onResult(null)
            }

            override fun onResponse(
                call: Call<ClientesDataCollectionItem>,
                response: Response<ClientesDataCollectionItem>
            ) {
                if (response.isSuccessful){
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                }
                else if (response.code() == 500){
                    val errorResponse = Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@Registrar,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@Registrar,"Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun cargarTiposDocumento(){
        val usuarioService : TipoDeDocumentoService = RestEngine.buildService().create(TipoDeDocumentoService::class.java)
        val result : Call<List<TipoDeDocumentoDataCollectionItem>> = usuarioService.listTipoDeDocumento()
        result.enqueue(object : Callback<List<TipoDeDocumentoDataCollectionItem>> {
            override fun onFailure(call: Call<List<TipoDeDocumentoDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@Registrar, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
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
                val listAdapter = ArrayAdapter(this@Registrar,android.R.layout.simple_list_item_1, arrayTipoDoc)
                spinnerTiposDoc.adapter = listAdapter
            }
        })
    }



}