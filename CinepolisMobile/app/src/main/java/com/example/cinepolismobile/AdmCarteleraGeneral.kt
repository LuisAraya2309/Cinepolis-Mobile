package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AdmCarteleraGeneral : AppCompatActivity() {

    private lateinit var  agregarFuncion : ImageView
    private lateinit var  editarFuncion : ImageView
    private lateinit var  eliminarFuncion : ImageView
    private lateinit var  volverFuncion : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_cartelera_general)

        agregarFuncion = findViewById(R.id.agregar)
        editarFuncion = findViewById(R.id.editar)
        eliminarFuncion = findViewById(R.id.eliminar)
        volverFuncion = findViewById(R.id.volver)

        agregarFuncion!!.setOnClickListener {
            val act : Intent = Intent(this,AdmAgregarCartelera::class.java)
            startActivity(act)
        }

        editarFuncion!!.setOnClickListener {
            val act : Intent = Intent(this,AdmEditarCartelera::class.java)
            startActivity(act)
        }

        eliminarFuncion!!.setOnClickListener {
            val act : Intent = Intent(this,AdmCarteleraEliminar::class.java)
            startActivity(act)
        }

        volverFuncion!!.setOnClickListener {
            val act : Intent = Intent(this,Dashboardadmin::class.java)
            startActivity(act)
        }


    }
}