package com.example.cinepolismobile

import android.graphics.Bitmap
import java.io.InputStream

class Pelicula(
    val titulo:String, val director:String,
    val añoPublicacion:String,
    val edadRequerida:String,
    val idioma:String,
    val imagen: InputStream,
    val idPelicula:Int,
    val hora:String
) {

}