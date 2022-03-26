package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class AdmAlimentos : AppCompatActivity() {

    private var modificarAlimento : ImageView? = null
    private var agregarAlimento : ImageView? = null
    private var eliminarAlimento : ImageView? = null
    private var regresar : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_alimentos)

        agregarAlimento = findViewById(R.id.agregar)
        agregarAlimento!!.setOnClickListener {
            //Recuperar la información de las películas

            //Pasar a la siguiente interfaz
            val agregarAct : Intent = Intent(this,AdmAlimentosAgregar::class.java)
            startActivity(agregarAct)
        }

        modificarAlimento = findViewById(R.id.modificar)
        modificarAlimento!!.setOnClickListener {

            val modificarAct : Intent = Intent(this,AdmAlimentosModificar::class.java)
            startActivity(modificarAct)
        }

        eliminarAlimento = findViewById(R.id.eliminar)
        eliminarAlimento!!.setOnClickListener {

            val eliminarAct : Intent = Intent(this,AdmAlimentosEliminar::class.java)
            startActivity(eliminarAct)
        }

        regresar = findViewById(R.id.regresar)
        regresar!!.setOnClickListener {

            val regresarAct : Intent = Intent(this,MainActivity::class.java)
            startActivity(regresarAct)
        }
    }
}