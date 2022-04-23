package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_cliente.*

class MenuCliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_cliente)

        btnVerProductos.setOnClickListener{
            val intent: Intent = Intent(this, VerProductos::class.java)
            startActivity(intent)
        }

        btnPedido.setOnClickListener{
            val intent: Intent = Intent(this, Pedido::class.java)
            startActivity(intent)
        }

        btnEditarCliente.setOnClickListener{
            val intent: Intent = Intent(this, EditarCliente::class.java)
            startActivity(intent)
        }
    }
}