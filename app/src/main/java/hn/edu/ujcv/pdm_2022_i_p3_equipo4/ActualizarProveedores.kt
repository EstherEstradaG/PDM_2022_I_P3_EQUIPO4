package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_actualizar_proveedores.*
import kotlinx.android.synthetic.main.activity_area_laboral.*

class ActualizarProveedores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_proveedores)


        btnMostrarProveedorActualizado.setOnClickListener{
            val intent: Intent = Intent(this, MostrarProveedores::class.java)
            startActivity(intent)
        }
    }
}