package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.AreaLaboralDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.RestApiError
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.entities.TiempoDeEntregaDataCollectionItem
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.AreaLaboralService
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.RestEngine
import hn.edu.ujcv.pdm_2022_i_p3_equipo4.services.TiempoDeEntregaService
import kotlinx.android.synthetic.main.activity_area_laboral.*
import kotlinx.android.synthetic.main.activity_categoria.*
import kotlinx.android.synthetic.main.activity_estimado_entregas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstimadoEntregas : AppCompatActivity() {

    var listaEstimadoEntrega : List<TiempoDeEntregaDataCollectionItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimado_entregas)

        btnMostrarEstimadoEntrega.setOnClickListener{
            val intent: Intent = Intent(this, MostrarEstimadoEntrega::class.java)
            startActivity(intent)
        }

        btnGuardarEntrega.setOnClickListener{
            callServiceEstimadoEntrega()
        }
    }

    private fun callServiceEstimadoEntrega(){
        val estimadoEntrega = TiempoDeEntregaDataCollectionItem(idTiempoDeEntrega = null,
            estimadoEntrega = txtEstimadoEntrega.text.toString(),
            descripcionEstimadoEntrega = txtDescripcionCategoria2.text.toString(),
            estadoTiempoDeEntrega = true
        )
        addEstimadoEntrega(estimadoEntrega){
            if (it?.idTiempoDeEntrega != null){
                Toast.makeText(this,"Te has registrado: "+it?.idTiempoDeEntrega, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun addEstimadoEntrega(EstimadoEntregaData: TiempoDeEntregaDataCollectionItem, onResult : (TiempoDeEntregaDataCollectionItem?) -> Unit){
        val TiempoDeEntregaService : TiempoDeEntregaService = RestEngine.buildService().create(
            TiempoDeEntregaService::class.java)
        val result : Call<TiempoDeEntregaDataCollectionItem> = TiempoDeEntregaService.addTiempoDeEntrega(EstimadoEntregaData)
        result.enqueue(object : Callback<TiempoDeEntregaDataCollectionItem> {
            override fun onFailure(call: Call<TiempoDeEntregaDataCollectionItem>, t: Throwable) {
                onResult(null)
            }
            override fun onResponse(
                call: Call<TiempoDeEntregaDataCollectionItem>,
                response: Response<TiempoDeEntregaDataCollectionItem>
            ) {
                if (response.isSuccessful){
                    val addedColonia = response.body()!!
                    onResult(addedColonia)
                } else if (response.code() == 500){
                    val errorResponse = Gson().fromJson(response.errorBody()!!.string(), RestApiError::class.java)
                    Toast.makeText(this@EstimadoEntregas,errorResponse.errorDetails, Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@EstimadoEntregas,"Fallo al traer el crear y traer el item.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}