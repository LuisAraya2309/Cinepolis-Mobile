package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class AdmClientes : AppCompatActivity() {

    private var modificarCliente : ImageView? = null
    private var agregarCliente : ImageView? = null
    private var eliminarCliente : ImageView? = null
    private var regresar : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_clientes)

        agregarCliente = findViewById(R.id.agregar)
        agregarCliente!!.setOnClickListener {
            //Recuperar la información de las películas

            //Pasar a la siguiente interfaz
            val agregarAct : Intent = Intent(this,AdmClientesAgregar::class.java)
            startActivity(agregarAct)
        }

        modificarCliente = findViewById(R.id.modificar)
        modificarCliente!!.setOnClickListener {

            val modificarAct : Intent = Intent(this,AdmClientesModificar::class.java)
            startActivity(modificarAct)
        }

        eliminarCliente = findViewById(R.id.eliminar)
        eliminarCliente!!.setOnClickListener {

            val eliminarAct : Intent = Intent(this,AdmClientesEliminar::class.java)
            startActivity(eliminarAct)
        }

        regresar = findViewById(R.id.regresar)
        regresar!!.setOnClickListener {

            val regresarAct : Intent = Intent(this,MainActivity::class.java)
            startActivity(regresarAct)
        }
    }
}