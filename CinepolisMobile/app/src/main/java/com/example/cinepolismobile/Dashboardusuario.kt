package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Dashboardusuario : AppCompatActivity() {

    private var verCartelera : ImageView? = null
    private var dulceria : ImageView? = null
    private var agregarMetodoPago : ImageView? = null
    private var carrito : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboardusuario)

        val idUsuario = intent.extras!!.getInt("idUsuario")

        verCartelera = findViewById(R.id.cartelera)

        verCartelera!!.setOnClickListener {
            //Recuperar la información de las películas

            //Pasar a la siguiente interfaz
            val carteleraAct : Intent = Intent(this,Cartelera::class.java)
            val idCliente = intent.extras!!.getInt("idUsuario")
            carteleraAct.putExtra("idCliente",idCliente)
            startActivity(carteleraAct)
        }

        agregarMetodoPago = findViewById(R.id.volver)
        agregarMetodoPago!!.setOnClickListener {

            val tarjetasAct : Intent = Intent(this,AgregarTarjeta::class.java)
            startActivity(tarjetasAct)
        }

        dulceria = findViewById(R.id.editar)
        dulceria!!.setOnClickListener {

            val DulceriaAct : Intent = Intent(this,Alimentos::class.java)
            DulceriaAct.putExtra("idUsuario",idUsuario)
            startActivity(DulceriaAct)
        }

        carrito = findViewById(R.id.carrito)
        carrito!!.setOnClickListener {

            val CarritoAct : Intent = Intent(this,CarritoCliente::class.java)
            CarritoAct.putExtra("idUsuario",idUsuario)
            startActivity(CarritoAct)
        }
    }
}