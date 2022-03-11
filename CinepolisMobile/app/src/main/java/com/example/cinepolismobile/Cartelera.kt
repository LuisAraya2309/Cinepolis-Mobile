package com.example.cinepolismobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.sql.PreparedStatement

class Cartelera : AppCompatActivity() {
    private var carteleraPeliculas : ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartelera)
        carteleraPeliculas  = findViewById<ListView>(R.id.cartelerapeliculas)

        val listaPeliculas = obtenerPelículas()  //Se obtienen las peliculas de la base de datos
        println(listaPeliculas)

        carteleraPeliculas!!.adapter = AdapterPeliculas(this,R.layout.list_item_cartelera,listaPeliculas)


        /*
        carteleraPeliculas!!.setOnItemClickListener{parent,view,position,id ->
            //Se recolecta la pelicula escogida y se inicia la interfaz de agregar boletos
            val peliculaSeleccionada = listaPeliculas[position].titulo // Esta entrando una pelicula o un string
            val pasarAgregarBoletos = Intent(this,Boletos::class.java)
            pasarAgregarBoletos.putExtra("pelicula",peliculaSeleccionada)
            //Iniciamos la actividad con el parametro de la pelicula
            startActivity(pasarAgregarBoletos)
        }
            */


    }

    fun obtenerPelículas(): List<Pelicula> {
        val conexionBD = ConexionBD()
        val objConexion = conexionBD.conectarDB()
        val consulta = "SELECT DISTINCT \n" +
                "\tP.Titulo,\n" +
                "\tP.Director,\n" +
                "\tP.AñoPublicacion,\n" +
                "\tP.EdadRequerida,\n" +
                "\tP.Idiomas,\n" +
                "\tP.Imagen\n" +
                "FROM dbo.Pelicula AS P\n" +
                "INNER JOIN dbo.Funciones AS F\n" +
                "ON F.IdPelicula = P.Id\n" +
                "WHERE F.Fecha = (CONVERT(DATE,GETDATE()))"
        val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
        val dataSet  = iniciarConexion?.executeQuery()
        val listaPeliculas = dataSet.use {
            generateSequence {
                if (dataSet!!.next()){
                    Pelicula(dataSet.getString(1), dataSet.getString(2), dataSet.getString(3), dataSet.getString(4), dataSet.getString(5),dataSet.getBinaryStream(6))
                }  else{
                    null
                }
            }.toList()
        }

        return listaPeliculas


    }
}


