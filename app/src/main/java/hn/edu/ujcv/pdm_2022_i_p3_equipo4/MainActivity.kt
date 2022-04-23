package hn.edu.ujcv.pdm_2022_i_p3_equipo4


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLoginCliente.setOnClickListener{
            val intent: Intent = Intent(this, LoginCliente::class.java)
            startActivity(intent)
        }

        btnLoginUsuario.setOnClickListener{
            val intent: Intent = Intent(this, LoginUsuario::class.java)
            startActivity(intent)
        }

    }



}