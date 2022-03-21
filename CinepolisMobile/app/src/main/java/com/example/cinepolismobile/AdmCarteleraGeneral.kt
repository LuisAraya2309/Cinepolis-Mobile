package com.example.cinepolismobile

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

        agregarFuncion.setOnClickListener {

        }


    }
}