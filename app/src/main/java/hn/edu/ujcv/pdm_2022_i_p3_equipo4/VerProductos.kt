package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ArticulosDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ArticulosService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_ver_productos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerProductos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_productos)

        callServiceGetAllProductos()

    }

    private fun callServiceGetAllProductos(){
        val productosService : ArticulosService = RestEngine.buildService().create(ArticulosService::class.java)
        val result : Call<List<ArticulosDataCollectionItem>> = productosService.listArticulo()
        result.enqueue(object : Callback<List<ArticulosDataCollectionItem>> {
            override fun onFailure(call: Call<List<ArticulosDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@VerProductos, "Error${t.localizedMessage}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<ArticulosDataCollectionItem>>,
                response: Response<List<ArticulosDataCollectionItem>>
            ) {
                val arrayProductos = arrayListOf<String>()
                for (item in response.body()!!){
                    arrayProductos.add("${item.idArticulos} ${item.nombreArticulo}")
                }
                val listAdapter = ArrayAdapter(this@VerProductos,android.R.layout.simple_list_item_1, arrayProductos)
                listViewProductos.adapter = listAdapter

            }
        })
    }

}