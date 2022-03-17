package com.example.cinepolismobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

object ControladorImagen {
    fun seleccionarImagen(actividad: Activity, codigo: Int) {
        val intento = Intent(Intent.ACTION_PICK)
        intento.type = "image/*"
        actividad.startActivityForResult(intento, codigo)
    }
}