package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.AreaLaboralDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ArticulosDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.AreaLaboralService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ArticulosService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_area_laboral.*
import kotlinx.android.synthetic.main.activity_articulos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Articulos : AppCompatActivity() {

    var listaAreaLaboral : List<AreaLaboralDataCollectionItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulos)

        btnMostrarArticulo.setOnClickListener{
            val intent: Intent = Intent(this, MostrarArticulos::class.java)
            startActivity(intent)
        }


        btnGuardarArticulo.setOnClickListener{
            callServiceArticulos()
        }

    }

    private fun callServiceArticulos(){
        val articulo = ArticulosDataCollectionItem(idArticulos = null,
            nombreArticulo = txtNombreAreaLaboral.text.toString(),
            stock = txtStock.text.toString().toInt(),
            descripcionArticulo = true,
            estadoArticulo = true,
                    idProveedor = null,
                    idCategoria = null,
                    idTiempoDeEntrega = null,
            precioArticulo = txtPrecioArticulo.text.toString().toDouble()

        )
        addArticulo(articulo){
            if (it?.idArticulos != null){
                Toast.makeText(this,"Te has registrado: "+it?.idArticulos, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun addArticulo(articuloData: ArticulosDataCollectionItem, onResult : (ArticulosDataCollectionItem?) -> Unit){
        val ArticulosService : ArticulosService = RestEngine.buildService().create(
            ArticulosService::class.java)
        val result : Call<ArticulosDataCollectionItem> = ArticulosService.addArticulo(articuloData)
        result.enqueue(object : Callback<ArticulosDataCollectionItem> {
            override fun onFailure(call: Call<ArticulosDataCollectionItem>, t: Throwable) {
                onResult(null)
            }
            override fun onResponse(
                call: Call<ArticulosDataCollectionItem>,
                response: Response<ArticulosDataCollectionItem>
            ) {
                if (response.isSuccessful){
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                } else if (response.code() == 500){
                    val errorResponse = Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@Articulos,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@Articulos,"Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}