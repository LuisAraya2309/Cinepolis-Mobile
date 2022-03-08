package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Dashboardusuario : AppCompatActivity() {

    private var verCartelera : ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboardusuario)

        verCartelera = findViewById(R.id.cartelera)

        verCartelera!!.setOnClickListener {
            //Recuperar la información de las películas

            //Pasar a la siguiente interfaz
            val carteleraAct : Intent = Intent(this,Cartelera::class.java)
            startActivity(carteleraAct)
        }
    }
}