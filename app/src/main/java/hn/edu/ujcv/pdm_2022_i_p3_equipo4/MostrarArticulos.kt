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
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.databinding.ActivityMostrarArticulosBinding
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ArticulosDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ArticulosService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_ver_productos.*
import kotlinx.android.synthetic.main.fragment_first5.*
import kotlinx.android.synthetic.main.fragment_first6.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostrarArticulos : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarArticulosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarArticulosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_articulos)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        btnActualizarArticulo.setOnClickListener{
            val intent: Intent = Intent(this, ActualizarArticulo::class.java)
            startActivity(intent)
        }
    }

    private fun callServiceGetAllArticulos(){
        val productosService : ArticulosService = RestEngine.buildService().create(ArticulosService::class.java)
        val result : Call<List<ArticulosDataCollectionItem>> = productosService.listArticulo()
        result.enqueue(object : Callback<List<ArticulosDataCollectionItem>> {
            override fun onFailure(call: Call<List<ArticulosDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@MostrarArticulos, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<ArticulosDataCollectionItem>>,
                response: Response<List<ArticulosDataCollectionItem>>
            ) {
                val arrayArticulos = arrayListOf<String>()
                for (item in response.body()!!){
                    arrayArticulos.add("${item.idArticulos} ${item.nombreArticulo} ${item.stock} ${item.descripcionArticulo}" +
                            "${item.precioArticulo}")
                }
                val listAdapter = ArrayAdapter(this@MostrarArticulos,android.R.layout.simple_list_item_1, arrayArticulos)
                listViewArticulos.adapter = listAdapter

            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_articulos)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}