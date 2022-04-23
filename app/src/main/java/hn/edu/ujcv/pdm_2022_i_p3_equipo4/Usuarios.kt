package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.AreaLaboralDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.UsuariosDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.AreaLaboralService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.UsuariosService
import kotlinx.android.synthetic.main.activity_area_laboral.*
import kotlinx.android.synthetic.main.activity_pedido.*
import kotlinx.android.synthetic.main.activity_usuarios.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class Usuarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuarios)


        btnMostrarUsuario.setOnClickListener{
            val intent: Intent = Intent(this, MostrarUsuarios::class.java)
            startActivity(intent)
        }

        btnGuardarCliente3.setOnClickListener{
            callServiceUsuario()
        }
    }

    private fun callServiceUsuario(){
        val usuarios = UsuariosDataCollectionItem(idUsuario = null,
            nombreEmpleado = txtNombreUsuarios.text.toString(),
            apellidoEmpleado = txtApellidoCliente3.text.toString(),
            nombreUsuario = txtNombreUsuario.text.toString(),
            contrasena = txtContraseñaUsuario.text.toString(),
            numeroDeIntentos = 0,
            estadoUsuario = true,
            admin = true,
            documentoEmpleado = txtDocumentoUsuario.text.toString(),
            idSexo = spnSexo.selectedItem.toString().split(" ")[0].toInt(),
            idTipoDeDocumento = spnTipoDocumento.selectedItem.toString().split(" ")[0].toInt(),
            FechaDeCambio = null,
            idAreaLaboral = spnAreaLaboral.selectedItem.toString().split(" ")[0].toInt()

        )


        addUsuario(usuarios){
            if (it?.idUsuario != null){
                Toast.makeText(this,"Te has registrado: "+it?.idUsuario, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addUsuario(usuariosData: UsuariosDataCollectionItem, onResult : (UsuariosDataCollectionItem?) -> Unit){
        val UsuariosService : UsuariosService = RestEngine.buildService().create(
            UsuariosService::class.java)
        val result : Call<UsuariosDataCollectionItem> = UsuariosService.addUsuarios(usuariosData)
        result.enqueue(object : Callback<UsuariosDataCollectionItem> {
            override fun onFailure(call: Call<UsuariosDataCollectionItem>, t: Throwable) {
                onResult(null)
            }
            override fun onResponse(
                call: Call<UsuariosDataCollectionItem>,
                response: Response<UsuariosDataCollectionItem>
            ) {
                if (response.isSuccessful){
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                } else if (response.code() == 500){
                    val errorResponse = Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@Usuarios,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@Usuarios,"Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
    }




}