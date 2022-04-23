package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_actualizar_area_laboral.*
import kotlinx.android.synthetic.main.activity_actualizar_tipo_entrega.*

class ActualizarAreaLaboral : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_area_laboral)

        btnMostrarAreaLaboralActualizada.setOnClickListener{
            val intent: Intent = Intent(this, MostrarAreaLaboral::class.java)
            startActivity(intent)
        }
    }
}