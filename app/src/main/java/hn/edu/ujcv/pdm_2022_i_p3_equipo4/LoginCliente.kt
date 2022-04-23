package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ClientesDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ClientesService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_login_cliente.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginCliente : AppCompatActivity() {

    var listaClientes : List<ClientesDataCollectionItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_cliente)
        callServiceGetAllClientes()


        btnIngresar.setOnClickListener{
            login()
        }

        btnRegistrarCliente.setOnClickListener{
            val intent: Intent = Intent(this, Registrar::class.java)
            startActivity(intent)
        }

    }

    private fun callServiceGetAllClientes(){
        val clientesService : ClientesService = RestEngine.buildService().create(ClientesService::class.java)
        val result : Call<List<ClientesDataCollectionItem>> = clientesService.listClientes()
        result.enqueue(object : Callback<List<ClientesDataCollectionItem>> {
            override fun onFailure(call: Call<List<ClientesDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@LoginCliente, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<ClientesDataCollectionItem>>,
                response: Response<List<ClientesDataCollectionItem>>
            ) {
                listaClientes = response.body()!!
            }
        })
    }

    private fun login(){
        var cliente : ClientesDataCollectionItem? = null
        for (item in listaClientes){
            if (editTextCorreoLoginCliente.text.toString() == item.correoCliente){
                cliente = item
            }
        }

        if (cliente == null){
            Toast.makeText(this,"No existe un cliente con ese correo.",Toast.LENGTH_SHORT).show()
            return
        }

        if (cliente.contrasenaCliente != editTextContraLoginCliente.text.toString()){
            Toast.makeText(this,"Contraseña incorrecta.",Toast.LENGTH_SHORT).show()
        }else{
            ClienteLogueado.clienteLogueado = cliente
            val intent = Intent(this, MenuCliente::class.java)
            startActivity(intent)
            finish()
        }
    }


}