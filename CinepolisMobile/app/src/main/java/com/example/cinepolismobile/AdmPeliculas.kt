package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AdmPeliculas : AppCompatActivity() {
    private var agregarPelicula : ImageView? = null
    private var modificarPelicula : ImageView? = null
    private var eliminarPelicula : ImageView? = null
    private var regresar : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_peliculas)

        agregarPelicula = findViewById(R.id.agregar)
        agregarPelicula!!.setOnClickListener {
            //Recuperar la información de las películas

            //Pasar a la siguiente interfaz
            val agregarAct : Intent = Intent(this,AdmPeliculasAgregar::class.java)
            startActivity(agregarAct)
        }

        modificarPelicula = findViewById(R.id.modificar)
        modificarPelicula!!.setOnClickListener {

            val modificarAct : Intent = Intent(this,AdmPeliculasModificar::class.java)
            startActivity(modificarAct)
        }

        eliminarPelicula = findViewById(R.id.eliminar)
        eliminarPelicula!!.setOnClickListener {

            val eliminarAct : Intent = Intent(this,AdmPeliculasEliminar::class.java)
            startActivity(eliminarAct)
        }

        regresar = findViewById(R.id.regresar)
        regresar!!.setOnClickListener {

            val regresarAct : Intent = Intent(this,Dashboardadmin::class.java)
            startActivity(regresarAct)
        }
    }
}