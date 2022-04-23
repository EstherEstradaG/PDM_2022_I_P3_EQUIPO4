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
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.databinding.ActivityMostrarRepartidoresBinding
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ArticulosDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RepartidorDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ArticulosService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RepartidorService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_ver_productos.*
import kotlinx.android.synthetic.main.fragment_first2.*
import kotlinx.android.synthetic.main.fragment_first6.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostrarRepartidores : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarRepartidoresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarRepartidoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_repartidores)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        btnActualizarRepartidor.setOnClickListener{
            val intent: Intent = Intent(this, ActualizarRepartidores::class.java)
            startActivity(intent)
        }

    }

    private fun callServiceGetAllRepartidor(){
        val repartidorService : RepartidorService = RestEngine.buildService().create(RepartidorService::class.java)
        val result : Call<List<RepartidorDataCollectionItem>> = repartidorService.listRepartidor()
        result.enqueue(object : Callback<List<RepartidorDataCollectionItem>> {
            override fun onFailure(call: Call<List<RepartidorDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@MostrarRepartidores, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<RepartidorDataCollectionItem>>,
                response: Response<List<RepartidorDataCollectionItem>>
            ) {
                val arrayRepartidores = arrayListOf<String>()
                for (item in response.body()!!){
                    arrayRepartidores.add("${item.idRepartidor} ${item.nombreRepartidor} ${item.apellidoRepartidor} ${item.telefonoRepartidor}" +
                            "${item.correoRepartidor} ${item.documentoRepartidor} ${item.idSexo} ${item.idTipoDeDocumento}")
                }
                val listAdapter = ArrayAdapter(this@MostrarRepartidores,android.R.layout.simple_list_item_1, arrayRepartidores)
                listViewRepartidores.adapter = listAdapter

            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_repartidores)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}