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
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.databinding.ActivityMostrarCategoriaBinding
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ArticulosDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.CategoriaDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ArticulosService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.CategoriaService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_ver_productos.*
import kotlinx.android.synthetic.main.fragment_first4.*
import kotlinx.android.synthetic.main.fragment_first6.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostrarCategoria : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarCategoriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarCategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_categoria)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        btnActualizarCategoria.setOnClickListener{
            val intent: Intent = Intent(this, ActualizarCategoria::class.java)
            startActivity(intent)
        }
    }

    private fun callServiceGetAllCategoria(){
        val categoriaService : CategoriaService = RestEngine.buildService().create(CategoriaService::class.java)
        val result : Call<List<CategoriaDataCollectionItem>> = categoriaService.listCategoria()
        result.enqueue(object : Callback<List<CategoriaDataCollectionItem>> {
            override fun onFailure(call: Call<List<CategoriaDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@MostrarCategoria, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<CategoriaDataCollectionItem>>,
                response: Response<List<CategoriaDataCollectionItem>>
            ) {
                val arrayCategoria = arrayListOf<String>()
                for (item in response.body()!!){
                    arrayCategoria.add("${item.idCategoria} ${item.nombreCategoria} ${item.descripcionCategoria}")
                }
                val listAdapter = ArrayAdapter(this@MostrarCategoria,android.R.layout.simple_list_item_1, arrayCategoria)
                listViewProductos.adapter = listAdapter

            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_categoria)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}