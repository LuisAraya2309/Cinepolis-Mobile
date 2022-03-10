package com.example.cinepolismobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class Cartelera : AppCompatActivity() {

    private var carteleraPeliculas : ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartelera)

        val peliculas = listOf("Arsenal","Chelsea","Manchester")
        carteleraPeliculas = findViewById(R.id.cartelerapeliculas)
        carteleraPeliculas!!.adapter = ArrayAdapter(this,R.layout.list_item_cartelera,peliculas)

    }
}