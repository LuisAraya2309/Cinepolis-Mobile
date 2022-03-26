package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Boletos : AppCompatActivity() {
    private lateinit var peliculaSeleccionada : TextView
    private lateinit var idiomaSeleccionado : TextView

    private lateinit var sumatercera : ImageView
    private lateinit var sumaadultos : ImageView
    private lateinit var sumaninos : ImageView

    private lateinit var restatercera : ImageView
    private lateinit var restaadultos : ImageView
    private lateinit var restaninos : ImageView

    private lateinit var qtytercera : TextView
    private lateinit var qtyadultos : TextView
    private lateinit var qtyninos : TextView

    private lateinit var totalplatas : TextView
    private lateinit var seleccionarasientos : Button

    private var totalAsientos : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boletos)

        peliculaSeleccionada = findViewById(R.id.nombrePelicula)
        idiomaSeleccionado = findViewById(R.id.idiomaSeleccionado)

        //Botones de suma
        sumatercera = findViewById(R.id.sumaterceraedad)
        sumaadultos = findViewById(R.id.sumaadultos)
        sumaninos = findViewById(R.id.sumaninos)

        //Botones de resta
        restatercera = findViewById(R.id.restaterceraedad)
        restaadultos = findViewById(R.id.restaadultos)
        restaninos = findViewById(R.id.restaninos)

        //Cantidad de boletos por edad
        qtytercera = findViewById(R.id.qtyterceredad)
        qtyadultos = findViewById(R.id.qtyadultos)
        qtyninos = findViewById(R.id.qtyninos)

        //Total de plata
        totalplatas = findViewById(R.id.totalplata)

        //Boton
        seleccionarasientos = findViewById(R.id.botonseleccionarasientos)

        val pelicula = intent.extras?.getString("pelicula")
        val idioma = intent.extras?.getString("idioma")
        val idPelicula = intent.extras?.getInt("idPelicula")
        val horaFuncion = intent.extras?.getString("horaFuncion")
        peliculaSeleccionada.text = pelicula.toString()
        idiomaSeleccionado.text = idioma.toString()

        //Sumas
        sumatercera.setOnClickListener {
            qtytercera.text = (qtytercera.text.toString().toInt() + 1).toString()
            totalplatas.text = (totalplatas.text.toString().toInt() + 2500).toString()
            totalAsientos +=1
        }
        sumaadultos.setOnClickListener {
            qtyadultos.text = (qtyadultos.text.toString().toInt() + 1).toString()
            totalplatas.text = (totalplatas.text.toString().toInt() + 3000).toString()
            totalAsientos +=1
        }
        sumaninos.setOnClickListener {
            qtyninos.text = (qtyninos.text.toString().toInt() + 1).toString()
            totalplatas.text = (totalplatas.text.toString().toInt() + 2100).toString()
            totalAsientos +=1
        }

        //Restas
        restatercera.setOnClickListener {
            if(qtytercera.text.toString().toInt() - 1>=0){
                qtytercera.text = (qtytercera.text.toString().toInt() - 1).toString()
                totalplatas.text = (totalplatas.text.toString().toInt() - 2500).toString()
                totalAsientos -=1
            }
        }
        restaadultos.setOnClickListener {
            if(qtyadultos.text.toString().toInt() - 1>=0){
                qtyadultos.text = (qtyadultos.text.toString().toInt() - 1).toString()
                totalplatas.text = (totalplatas.text.toString().toInt() - 3000).toString()
                totalAsientos -=1
            }
        }
        restaninos.setOnClickListener {
            if(qtyninos.text.toString().toInt() - 1>=0){
                qtyninos.text = (qtyninos.text.toString().toInt() - 1).toString()
                totalplatas.text = (totalplatas.text.toString().toInt() - 2100).toString()
                totalAsientos -=1
            }
        }

        seleccionarasientos.setOnClickListener {
            var pasarSeleccion : Intent = Intent(this,Seleccion::class.java)
            pasarSeleccion.putExtra("pelicula",pelicula)
            pasarSeleccion.putExtra("idioma",idioma)
            pasarSeleccion.putExtra("idPelicula",idPelicula)
            pasarSeleccion.putExtra("horaFuncion",horaFuncion)
            pasarSeleccion.putExtra("cantidadAsientos",totalAsientos)
            pasarSeleccion.putExtra("costoTotal",totalplatas.text.toString().toInt())
            val idCliente = intent.extras!!.getInt("idCliente")
            pasarSeleccion.putExtra("idCliente",idCliente)
            startActivity(pasarSeleccion)
        }

    }
}