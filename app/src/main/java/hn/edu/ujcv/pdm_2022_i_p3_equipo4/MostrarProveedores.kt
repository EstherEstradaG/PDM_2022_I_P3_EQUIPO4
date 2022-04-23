package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.databinding.ActivityMostrarProveedoresBinding
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ArticulosDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ProveedoresDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ArticulosService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ProveedoresService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_mostrar_proveedores.*
import kotlinx.android.synthetic.main.activity_ver_productos.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first6.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostrarProveedores : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarProveedoresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarProveedoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_proveedores)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        btnActualizarProveedor.setOnClickListener{
            val intent: Intent = Intent(this, ActualizarProveedores::class.java)
            startActivity(intent)
        }

    }

    private fun callServiceGetAllProveedores(){
        val proveedoresService : ProveedoresService = RestEngine.buildService().create(ProveedoresService::class.java)
        val result : Call<List<ProveedoresDataCollectionItem>> = proveedoresService.listProveedores()
        result.enqueue(object : Callback<List<ProveedoresDataCollectionItem>> {
            override fun onFailure(call: Call<List<ProveedoresDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@MostrarProveedores, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<ProveedoresDataCollectionItem>>,
                response: Response<List<ProveedoresDataCollectionItem>>
            ) {
                val arrayProveedores = arrayListOf<String>()
                for (item in response.body()!!){
                    arrayProveedores.add("${item.idProveedor} ${item.nombreProveedor} ${item.telefonoProveedor}" +
                            "${item.correoProveedor} ${item.documentoProveedor}")
                }
                val listAdapter = ArrayAdapter(this@MostrarProveedores,android.R.layout.simple_list_item_1, arrayProveedores)
                listViewProveedores.adapter = listAdapter

            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_proveedores)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}