package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_cliente.*
import kotlinx.android.synthetic.main.activity_menu_usuario.*

class MenuUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_usuario)

        btnProveedores.setOnClickListener{
            val intent: Intent = Intent(this, Proveedores::class.java)
            startActivity(intent)
        }

        btnRepartidores.setOnClickListener{
            val intent: Intent = Intent(this, Repartidores::class.java)
            startActivity(intent)
        }

        btnUsuarios.setOnClickListener{
            val intent: Intent = Intent(this, Usuarios::class.java)
            startActivity(intent)
        }

        btnAreaLaboral.setOnClickListener{
            val intent: Intent = Intent(this, AreaLaboral::class.java)
            startActivity(intent)
        }

        btnArticulos.setOnClickListener{
            val intent: Intent = Intent(this, Articulos::class.java)
            startActivity(intent)
        }


        btnCategoria.setOnClickListener{
            val intent: Intent = Intent(this, Categoria::class.java)
            startActivity(intent)
        }


        btnEntregas.setOnClickListener{
            val intent: Intent = Intent(this, EstimadoEntregas::class.java)
            startActivity(intent)
        }


        btnTipoEntrega.setOnClickListener{
            val intent: Intent = Intent(this, TipoEntrega::class.java)
            startActivity(intent)
        }


        btnMostrarClientes.setOnClickListener{
            val intent: Intent = Intent(this, MostrarClientes::class.java)
            startActivity(intent)
        }



    }
}