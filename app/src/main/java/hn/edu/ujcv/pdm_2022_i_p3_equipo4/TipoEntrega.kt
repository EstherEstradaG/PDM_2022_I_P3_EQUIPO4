package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.AreaLaboralDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TipoDeEntregaDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.AreaLaboralService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.TipoDeEntregaService
import kotlinx.android.synthetic.main.activity_area_laboral.*
import kotlinx.android.synthetic.main.activity_estimado_entregas.*
import kotlinx.android.synthetic.main.activity_tipo_entrega.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipoEntrega : AppCompatActivity() {

    var listaTipoEntrega : List<TipoDeEntregaDataCollectionItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo_entrega)

        btnMostrarTipoEntrega.setOnClickListener{
            val intent: Intent = Intent(this, MostrarTipoEntrega::class.java)
            startActivity(intent)
        }

        btnGuardarTipoEntrega.setOnClickListener{
            callServiceTipoEntrega()
        }
    }

    private fun callServiceTipoEntrega(){
        val tipoEntrega = TipoDeEntregaDataCollectionItem(idTipoDeEntrega = null,
            nombreTipoDeEntrega = txtTipoEntrega.text.toString(),
            descripcionTipoDeEntrega = txtDescripcionTipoEntrega.text.toString(),
            estadoTipoDeEntrega = true
        )
        addTipoEntrega(tipoEntrega){
            if (it?.idTipoDeEntrega != null){
                Toast.makeText(this,"Te has registrado: "+it?.idTipoDeEntrega, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addTipoEntrega(tipoEntregaData: TipoDeEntregaDataCollectionItem, onResult : (TipoDeEntregaDataCollectionItem?) -> Unit){
        val TipoDeEntregaService : TipoDeEntregaService = RestEngine.buildService().create(
            TipoDeEntregaService::class.java)
        val result : Call<TipoDeEntregaDataCollectionItem> = TipoDeEntregaService.addTipoDeEntrega(tipoEntregaData)
        result.enqueue(object : Callback<TipoDeEntregaDataCollectionItem> {
            override fun onFailure(call: Call<TipoDeEntregaDataCollectionItem>, t: Throwable) {
                onResult(null)
            }
            override fun onResponse(
                call: Call<TipoDeEntregaDataCollectionItem>,
                response: Response<TipoDeEntregaDataCollectionItem>
            ) {
                if (response.isSuccessful){
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                } else if (response.code() == 500){
                    val errorResponse = Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@TipoEntrega,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@TipoEntrega,"Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}