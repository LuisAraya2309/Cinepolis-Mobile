package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Dashboardadmin : AppCompatActivity() {
    private var gestionarPeliculas : ImageView? = null
    private var gestionarCartelera : ImageView? = null
    private var gestionarAlimentos : ImageView? = null
    private var gestionarClientes : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboardadmin)

        gestionarPeliculas = findViewById(R.id.gesPeliculas)
        gestionarCartelera = findViewById(R.id.gesCartelera)
        gestionarAlimentos = findViewById(R.id.gesAlimentos)
        gestionarClientes = findViewById(R.id.gesClientes)

        gestionarPeliculas!!.setOnClickListener {
            val act : Intent = Intent(this,AdmPeliculas::class.java)
            startActivity(act)
        }

        gestionarCartelera!!.setOnClickListener {
            val act : Intent = Intent(this,AdmCarteleraGeneral::class.java)
            startActivity(act)
        }

        gestionarAlimentos!!.setOnClickListener {
            val act : Intent = Intent(this,AdmAlimentos::class.java)
            startActivity(act)
        }

        gestionarClientes!!.setOnClickListener {
            val act : Intent = Intent(this,AdmClientes::class.java)
            startActivity(act)
        }
    }
}