package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ClientesDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ClientesService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_editar_cliente.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cliente)
        llenarCampos()

        btnGuardarCliente2.setOnClickListener {
            callServiceUpdateCliente()
        }
    }

    private fun llenarCampos(){
        txtNombreCliente2.setText(ClienteLogueado.clienteLogueado!!.nombreCliente)
        txtApellidoCliente2.setText(ClienteLogueado.clienteLogueado!!.apellidoCliente)
        txtTelefonoCliente2.setText(ClienteLogueado.clienteLogueado!!.telefonoCliente)
        txtDireccionCliente.setText(ClienteLogueado.clienteLogueado!!.direccionCliente)
        txtCorreoCliente2.setText(ClienteLogueado.clienteLogueado!!.correoCliente)
        txtDocumentoCliente2.setText(ClienteLogueado.clienteLogueado!!.documentoCliente)

    }

    private fun callServiceUpdateCliente(){
        val cliente = ClientesDataCollectionItem(idCliente = ClienteLogueado.clienteLogueado!!.idCliente,
            nombreCliente = txtNombreCliente2.text.toString(),
            apellidoCliente = txtApellidoCliente2.text.toString(),
            telefonoCliente = txtTelefonoCliente2.text.toString(),
            direccionCliente = txtDireccionCliente.text.toString(),
            correoCliente = txtCorreoCliente2.text.toString(),
            estadoCliente = true,
            contrasenaCliente = ClienteLogueado.clienteLogueado!!.contrasenaCliente,
            documentoCliente = txtDocumentoCliente2.text.toString(),
            idTipoDeDocumento = ClienteLogueado.clienteLogueado!!.idTipoDeDocumento
        )
        updateCliente(cliente){
            if (it?.idCliente != null){
                Toast.makeText(this,"Datos Actualizados",Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, MenuCliente::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateCliente(clienteData: ClientesDataCollectionItem, onResult : (ClientesDataCollectionItem?) -> Unit){
        val clientesService : ClientesService = RestEngine.buildService().create(ClientesService::class.java)
        val result : Call<ClientesDataCollectionItem> = clientesService.updateCliente(clienteData)
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
                    Toast.makeText(this@EditarCliente,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@EditarCliente,"Fallo al editar y traer el item.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}