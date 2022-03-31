package com.example.cinepolismobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class AdmPeliculasModificar : AppCompatActivity() {
    private val seleccionarAct = 1
    private lateinit var agregarFoto: Button
    private lateinit var uriImagen: Uri
    private var agregarPelicula: Button? = null
    private var conexionBase = ConexionBD()
    private lateinit var contexto: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_peliculas_agregar)

        agregarFoto = findViewById(R.id.agregarFoto)
        agregarFoto.setOnClickListener {
            ControladorImagen.seleccionarImagen(this, seleccionarAct)
        }

        agregarPelicula = findViewById(R.id.agregarPelicula)
        agregarPelicula!!.setOnClickListener {

            val objConexion: Connection? = conexionBase.conectarDB()  //Crear el objeto conexion

            val tituloBuscar: String =
                (findViewById<View>(R.id.inTituloBuscar) as EditText).text.toString()
            val titulo: String = (findViewById<View>(R.id.inTitulo) as EditText).text.toString()
            val director: String = (findViewById<View>(R.id.inDirector) as EditText).text.toString()
            val publicacion: Int =
                ((findViewById<View>(R.id.inPublicacion) as EditText).text.toString()).toInt()
            val idiomas: String = (findViewById<View>(R.id.inIdiomas) as EditText).text.toString()
            val generos: String = (findViewById<View>(R.id.inGeneros) as EditText).text.toString()
            val actores: String = (findViewById<View>(R.id.inActores) as EditText).text.toString()
            val duracion: Int =
                ((findViewById<View>(R.id.inDuracion) as EditText).text.toString()).toInt()
            val edadRequerida: Int =
                ((findViewById<View>(R.id.inEdadRequerida) as EditText).text.toString()).toInt()

            contexto = this@AdmPeliculasModificar
            val imagen = contexto.contentResolver.openInputStream(uriImagen)?.readBytes()!!

            val consulta = "EXECUTE sp_ModificarPelicula ?,?,?,?,?,?,?,?,?,?"
            val iniciarConexion: PreparedStatement? =
                conexionBase.prepararConsulta(objConexion, consulta)

            iniciarConexion?.setString(1, tituloBuscar)
            iniciarConexion?.setString(2, titulo)
            iniciarConexion?.setString(3, director)
            iniciarConexion?.setString(4, actores)
            iniciarConexion?.setString(5, generos)
            iniciarConexion?.setString(6, idiomas)
            iniciarConexion?.setInt(7, publicacion)
            iniciarConexion?.setInt(8, duracion)
            iniciarConexion?.setInt(9, edadRequerida)
            iniciarConexion?.setBytes(10, imagen)

            val dataSet: ResultSet? = iniciarConexion?.executeQuery()

            if (dataSet!!.next()) {
                var outResultCode: Int?
                outResultCode = dataSet.getInt("Codigo")

                if (outResultCode == 0) {
                    Toast.makeText(this, "¡Producto modificado con exito!", Toast.LENGTH_LONG)
                        .show()

                    //Pasar al gestor de peliculas
                    val gesPeliculas: Intent = Intent(this, AdmPeliculas::class.java)
                    startActivity(gesPeliculas)
                } else if (outResultCode == 1) {
                    Toast.makeText(
                        this,
                        "No se encontro el titulo de la pelicula",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(this, "Información invalida", Toast.LENGTH_LONG).show()
                }
            }

            //Cerrar la conexion
            objConexion?.close()

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == seleccionarAct && resultCode == Activity.RESULT_OK -> {
                uriImagen = data!!.data!!

            }
        }
    }
}