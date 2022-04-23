package hn.edu.ujcv.pdm_2022_i_p3_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_cliente.*
import kotlinx.android.synthetic.main.activity_login_usuario.*

class LoginUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_usuario)

        btnIngresarUsuario.setOnClickListener{
            val intent: Intent = Intent(this, MenuUsuario::class.java)
            startActivity(intent)
        }
    }
}