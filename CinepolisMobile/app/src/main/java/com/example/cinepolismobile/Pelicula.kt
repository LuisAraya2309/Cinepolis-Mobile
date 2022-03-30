package com.example.cinepolismobile

import android.graphics.Bitmap
import java.io.InputStream
import java.sql.Blob

class Pelicula(
    val titulo:String, val director:String,
    val añoPublicacion:String,
    val edadRequerida:String,
    val idioma:String,
    val imagen: Blob,
    val idPelicula:Int,
    val hora:String
) {

}