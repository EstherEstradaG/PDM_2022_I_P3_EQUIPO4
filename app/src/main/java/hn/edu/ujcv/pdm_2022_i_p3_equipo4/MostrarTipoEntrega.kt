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
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.databinding.ActivityMostrarTipoEntregaBinding
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.CategoriaDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TipoDeEntregaDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.CategoriaService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.TipoDeEntregaService
import kotlinx.android.synthetic.main.activity_mostrar_tipo_entrega.*
import kotlinx.android.synthetic.main.activity_ver_productos.*
import kotlinx.android.synthetic.main.fragment_first6.*
import kotlinx.android.synthetic.main.fragment_first9.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostrarTipoEntrega : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarTipoEntregaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarTipoEntregaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_tipo_entrega)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        btnActualizarTipoEntrega.setOnClickListener{
            val intent: Intent = Intent(this, ActualizarTipoEntrega::class.java)
            startActivity(intent)
        }

    }

    private fun callServiceGetAllTipoEntrega(){
        val tipoDeEntregaService : TipoDeEntregaService = RestEngine.buildService().create(TipoDeEntregaService::class.java)
        val result : Call<List<TipoDeEntregaDataCollectionItem>> = tipoDeEntregaService.listTipoDeEntrega()
        result.enqueue(object : Callback<List<TipoDeEntregaDataCollectionItem>> {
            override fun onFailure(call: Call<List<TipoDeEntregaDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@MostrarTipoEntrega, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<TipoDeEntregaDataCollectionItem>>,
                response: Response<List<TipoDeEntregaDataCollectionItem>>
            ) {
                val arrayTipoDeEntrega = arrayListOf<String>()
                for (item in response.body()!!){
                    arrayTipoDeEntrega.add("${item.idTipoDeEntrega} ${item.nombreTipoDeEntrega} ${item.descripcionTipoDeEntrega}")
                }
                val listAdapter = ArrayAdapter(this@MostrarTipoEntrega,android.R.layout.simple_list_item_1, arrayTipoDeEntrega)
                listViewTipoEntrega.adapter = listAdapter

            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_tipo_entrega)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}