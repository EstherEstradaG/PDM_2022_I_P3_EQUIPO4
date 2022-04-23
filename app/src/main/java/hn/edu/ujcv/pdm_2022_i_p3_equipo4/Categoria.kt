package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.AreaLaboralDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.CategoriaDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.AreaLaboralService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.CategoriaService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_area_laboral.*
import kotlinx.android.synthetic.main.activity_categoria.*
import kotlinx.android.synthetic.main.activity_tipo_entrega.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Categoria : AppCompatActivity() {

    var ListaCategoria : List<CategoriaDataCollectionItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria)

        btnMostrarCategoria.setOnClickListener{
            val intent: Intent = Intent(this, MostrarCategoria::class.java)
            startActivity(intent)
        }

        btnGuardarCategoria.setOnClickListener{
            callServiceCategoria()
        }

    }

    private fun callServiceCategoria(){
        val categoria = CategoriaDataCollectionItem(idCategoria = null,
            nombreCategoria = txtNombreCategoria.text.toString(),
            descripcionCategoria = txtDescripcionCategoria.text.toString(),
            estadoCategoria = true
        )
        addCategoria(categoria){
            if (it?.idCategoria != null){
                Toast.makeText(this,"Te has registrado: "+it?.idCategoria, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun addCategoria(categoriaData: CategoriaDataCollectionItem, onResult : (CategoriaDataCollectionItem?) -> Unit){
        val CategoriaService : CategoriaService = RestEngine.buildService().create(
            CategoriaService::class.java)
        val result : Call<CategoriaDataCollectionItem> = CategoriaService.addCategoria(categoriaData)
        result.enqueue(object : Callback<CategoriaDataCollectionItem> {
            override fun onFailure(call: Call<CategoriaDataCollectionItem>, t: Throwable) {
                onResult(null)
            }
            override fun onResponse(
                call: Call<CategoriaDataCollectionItem>,
                response: Response<CategoriaDataCollectionItem>
            ) {
                if (response.isSuccessful){
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                } else if (response.code() == 500){
                    val errorResponse = Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@Categoria,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@Categoria,"Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}