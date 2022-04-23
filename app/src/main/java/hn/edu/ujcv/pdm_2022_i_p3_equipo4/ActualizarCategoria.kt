package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_actualizar_categoria.*
import kotlinx.android.synthetic.main.activity_area_laboral.*

class ActualizarCategoria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_categoria)


        btnMostrarCategoriaActualizada.setOnClickListener{
            val intent: Intent = Intent(this, MostrarCategoria::class.java)
            startActivity(intent)
        }
    }
}