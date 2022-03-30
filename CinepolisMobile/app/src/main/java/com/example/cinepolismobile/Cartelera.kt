package com.example.cinepolismobile

import android.content.Intent
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


        carteleraPeliculas!!.adapter = AdapterPeliculas(this,R.layout.list_item_cartelera,listaPeliculas)

        carteleraPeliculas!!.setOnItemClickListener{parent,view,position,id ->
            //Se recolecta la pelicula escogida y se inicia la interfaz de agregar boletos
            val peliculaSeleccionada = listaPeliculas[position].titulo // Esta entrando una pelicula o un string
            var listaIdiomas:String = listaPeliculas[position].idioma
            val idPelicula : Int = listaPeliculas[position].idPelicula
            val horaFuncion : String = listaPeliculas[position].hora
            var idiomaSeleccionado =""
            var idiomasParseados = listaIdiomas.split(',')
            if(idiomasParseados.size>1){
                idiomaSeleccionado = "SUB"
            }
            else{
                idiomaSeleccionado = "DOB"
            }
            val pasarAgregarBoletos : Intent = Intent(this,Boletos::class.java)
            pasarAgregarBoletos.putExtra("pelicula",peliculaSeleccionada)
            pasarAgregarBoletos.putExtra("idioma",idiomaSeleccionado)
            pasarAgregarBoletos.putExtra("idPelicula",idPelicula)
            pasarAgregarBoletos.putExtra("horaFuncion",horaFuncion)
            val idCliente = intent.extras!!.getInt("idCliente")
            pasarAgregarBoletos.putExtra("idCliente",idCliente)
            startActivity(pasarAgregarBoletos)
        }



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
                "\tP.Imagen,\n" +
                "\tP.Id,\n" +
                "\tF.Hora\n" +
                "FROM dbo.Pelicula AS P\n" +
                "INNER JOIN dbo.Funciones AS F\n" +
                "ON F.IdPelicula = P.Id\n" +
                "WHERE F.Fecha = '2022-03-10'"   //(CONVERT(DATE,GETDATE()))
        val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
        val dataSet  = iniciarConexion?.executeQuery()
        val listaPeliculas = dataSet.use {
            generateSequence {
                if (dataSet!!.next()){
                    Pelicula(dataSet.getString(1), dataSet.getString(2), dataSet.getString(3), dataSet.getString(4), dataSet.getString(5),dataSet.getBlob(6),dataSet.getInt(7),dataSet.getString(8))
                }  else{
                    null
                }
            }.toList()
        }

        return listaPeliculas
    }
}


