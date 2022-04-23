package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_actualizar_repartidores.*
import kotlinx.android.synthetic.main.activity_actualizar_tipo_entrega.*
import kotlinx.android.synthetic.main.activity_repartidores.*

class ActualizarRepartidores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_repartidores)

        btnMostrarRepartidorActualizada.setOnClickListener{
            val intent: Intent = Intent(this, MostrarRepartidores::class.java)
            startActivity(intent)
        }

    }
}