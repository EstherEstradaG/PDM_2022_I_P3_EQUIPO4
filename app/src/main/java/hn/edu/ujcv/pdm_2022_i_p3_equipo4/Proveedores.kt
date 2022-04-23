package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ProveedoresDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.UsuariosDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ProveedoresService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.UsuariosService
import kotlinx.android.synthetic.main.activity_estimado_entregas.*
import kotlinx.android.synthetic.main.activity_proveedores.*
import kotlinx.android.synthetic.main.activity_repartidores.*
import kotlinx.android.synthetic.main.activity_usuarios.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Proveedores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proveedores)

        btnMostrarProveedor.setOnClickListener {
            val intent: Intent = Intent(this, MostrarProveedores::class.java)
            startActivity(intent)
        }

        btnGuardarProveedor.setOnClickListener{
            callServiceProveedores()
        }

    }

        private fun callServiceProveedores(){
            val proveedores = ProveedoresDataCollectionItem(idProveedor = null,
                nombreProveedor = txtNombreUsuarios.text.toString(),
                telefonoProveedor = txtTelefono.text.toString(),
                correoProveedor = txtCorreoProveedor.text.toString(),
                estadoProveedor = true,
                documentoProveedor = txtDocumento.text.toString(),
                idTipoDeDocumento = spnTipoDocumentoProveedor.selectedItem.toString().split(" ")[0].toInt()

            )


            addProveedor(proveedores){
                if (it?.idProveedor != null){
                    Toast.makeText(this,"Te has registrado: "+it?.idProveedor, Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun addProveedor(proveedoresData: ProveedoresDataCollectionItem, onResult : (ProveedoresDataCollectionItem?) -> Unit){
            val ProveedoresService : ProveedoresService = RestEngine.buildService().create(
                ProveedoresService::class.java)
            val result : Call<ProveedoresDataCollectionItem> = ProveedoresService.addProveedor(proveedoresData)
            result.enqueue(object : Callback<ProveedoresDataCollectionItem> {
                override fun onFailure(call: Call<ProveedoresDataCollectionItem>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(
                    call: Call<ProveedoresDataCollectionItem>,
                    response: Response<ProveedoresDataCollectionItem>
                ) {
                    if (response.isSuccessful){
                        val addedColonia = response.body()!!
                        onResult(addedColonia)
                    } else if (response.code() == 500){
                        val errorResponse = Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                        Toast.makeText(this@Proveedores,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                    }else
                    {
                        Toast.makeText(this@Proveedores,"Fallo al traer el crear y traer el item.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }



}