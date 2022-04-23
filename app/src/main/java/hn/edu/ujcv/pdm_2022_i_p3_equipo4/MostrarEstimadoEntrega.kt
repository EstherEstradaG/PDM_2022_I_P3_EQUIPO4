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
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.databinding.ActivityMostrarEstimadoEntregaBinding
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.CategoriaDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TiempoDeEntregaDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.CategoriaService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.TiempoDeEntregaService
import kotlinx.android.synthetic.main.activity_ver_productos.*
import kotlinx.android.synthetic.main.fragment_first10.*
import kotlinx.android.synthetic.main.fragment_first6.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostrarEstimadoEntrega : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarEstimadoEntregaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarEstimadoEntregaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController =
            findNavController(R.id.nav_host_fragment_content_mostrar_estimado_entrega)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        btnActualizarEstimadoEntrega.setOnClickListener{
            val intent: Intent = Intent(this, ActualizarEstimadoEntrega::class.java)
            startActivity(intent)
        }
    }

    private fun callServiceGetEstimado(){
        val estimadoService : TiempoDeEntregaService = RestEngine.buildService().create(TiempoDeEntregaService::class.java)
        val result : Call<List<TiempoDeEntregaDataCollectionItem>> = estimadoService.listTiempoDeEntrega()
        result.enqueue(object : Callback<List<TiempoDeEntregaDataCollectionItem>> {
            override fun onFailure(call: Call<List<TiempoDeEntregaDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@MostrarEstimadoEntrega, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<TiempoDeEntregaDataCollectionItem>>,
                response: Response<List<TiempoDeEntregaDataCollectionItem>>
            ) {
                val arrayEstimadoEntrega = arrayListOf<String>()
                for (item in response.body()!!){
                    arrayEstimadoEntrega.add("${item.idTiempoDeEntrega} ${item.estimadoEntrega} ${item.descripcionEstimadoEntrega}")
                }
                val listAdapter = ArrayAdapter(this@MostrarEstimadoEntrega,android.R.layout.simple_list_item_1, arrayEstimadoEntrega)
                listViewEstimadoEntrega.adapter = listAdapter

            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(R.id.nav_host_fragment_content_mostrar_estimado_entrega)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}