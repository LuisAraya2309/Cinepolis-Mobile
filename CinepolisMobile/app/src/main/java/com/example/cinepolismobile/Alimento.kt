package com.example.cinepolismobile

import android.graphics.Bitmap
import java.io.InputStream
import java.sql.Blob

class Alimento(
    val nombre:String,
    val tipo:String,
    val precio:Int,
    val imagen: Blob,
) {

}