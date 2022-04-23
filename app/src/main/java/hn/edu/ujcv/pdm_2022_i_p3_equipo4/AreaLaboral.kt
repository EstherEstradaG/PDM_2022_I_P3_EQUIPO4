package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.AreaLaboralDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.ClientesDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.AreaLaboralService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.ClientesService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import kotlinx.android.synthetic.main.activity_area_laboral.*
import kotlinx.android.synthetic.main.activity_login_cliente.*
import kotlinx.android.synthetic.main.activity_menu_usuario.*
import kotlinx.android.synthetic.main.activity_registrar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AreaLaboral : AppCompatActivity() {


    var listaAreaLaboral : List<AreaLaboralDataCollectionItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_laboral)


        btnMostrarAreaLaboral.setOnClickListener{
            val intent: Intent = Intent(this, MostrarAreaLaboral::class.java)
            startActivity(intent)
        }

        btnGuardarAreaLaboral.setOnClickListener{
            callServiceAreaLaboral()
        }


    }

    private fun callServiceAreaLaboral(){
        val areaLaboral = AreaLaboralDataCollectionItem(idAreaLaboral = null,
            nombreAreaLaboral = txtNombreAreaLaboral.text.toString(),
            descripcionAreaLaboral = txtDescripcionAreaLaboral.text.toString(),
            estadoAreaLaboral = true
        )
        addAreaLaboral(areaLaboral){
            if (it?.idAreaLaboral != null){
                Toast.makeText(this,"Te has registrado: "+it?.idAreaLaboral,Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun addAreaLaboral(areaLaboralData: AreaLaboralDataCollectionItem, onResult : (AreaLaboralDataCollectionItem?) -> Unit){
        val AreaLaboralService : AreaLaboralService = RestEngine.buildService().create(AreaLaboralService::class.java)
        val result : Call<AreaLaboralDataCollectionItem> = AreaLaboralService.addAreaLaboral(areaLaboralData)
        result.enqueue(object : Callback<AreaLaboralDataCollectionItem> {
            override fun onFailure(call: Call<AreaLaboralDataCollectionItem>, t: Throwable) {
                onResult(null)
            }
            override fun onResponse(
                call: Call<AreaLaboralDataCollectionItem>,
                response: Response<AreaLaboralDataCollectionItem>
            ) {
                if (response.isSuccessful){
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                } else if (response.code() == 500){
                    val errorResponse = Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@AreaLaboral,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@AreaLaboral,"Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}