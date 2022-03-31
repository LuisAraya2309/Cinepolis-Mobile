package com.example.cinepolismobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class AdmPeliculasEliminar : AppCompatActivity() {

    private var EliminarPeliculas : Button? = null
    private var conexionBase = ConexionBD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_peliculas_eliminar)

        var objConexion : Connection? = conexionBase.conectarDB()
        var dataSet : ResultSet?

        val spinner = findViewById<Spinner>(R.id.peliculas)
        val listaPeliculas = obtenerPeliculas()
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item,listaPeliculas)
        spinner.adapter = adaptador


        EliminarPeliculas = findViewById(R.id.eliminarPelicula)
        EliminarPeliculas!!.setOnClickListener {
            objConexion = conexionBase.conectarDB()  //Crear el objeto conexion
            val pelicula: String = (findViewById<View>(R.id.peliculas) as Spinner).selectedItem.toString()

            val consulta = "EXECUTE sp_EliminarPelicula ?"
            val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            iniciarConexion?.setString(1,pelicula.toString())
            dataSet = iniciarConexion?.executeQuery()

            if(dataSet!!.next()){
                var outResultCode : Int?
                outResultCode = dataSet!!.getInt("Codigo")

                if(outResultCode==0){
                    Toast.makeText(this,"¡Pelicula eliminada con éxito!", Toast.LENGTH_LONG).show()

                    //Pasar al inicio de sesion
                    val dashbordAdmPelicula : Intent = Intent(this,AdmPeliculas::class.java)
                    startActivity(dashbordAdmPelicula)
                }
                else{
                    Toast.makeText(this,"Información inválida", Toast.LENGTH_LONG).show()
                }
            }
            //Cerrar la conexion
            objConexion?.close()
        }
    }



    fun obtenerPeliculas(): List<String> {
        val conexionBD = ConexionBD()
        val objConexion = conexionBD.conectarDB()
        val consulta = "EXECUTE sp_MostrarPeliculas"
        val iniciarConexion: PreparedStatement? = conexionBD.prepararConsulta(objConexion, consulta)
        val dataSet = iniciarConexion?.executeQuery()
        val listaPeliculas = dataSet.use {
            generateSequence {
                if (dataSet!!.next()) {
                    dataSet.getString("Titulo")
                } else {
                    null
                }
            }.toList()
        }
        return listaPeliculas
    }
}