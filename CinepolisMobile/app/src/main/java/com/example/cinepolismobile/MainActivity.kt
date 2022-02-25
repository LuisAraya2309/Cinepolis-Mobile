package com.example.cinepolismobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class MainActivity : AppCompatActivity() {

    private var iniciarSesion : Button ? = null
    private var conexionBase = ConexionBD()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asociar boton de inicio de sesion
        iniciarSesion = findViewById(R.id.iniciosesion)

        //Funcion del boton de iniciar sesion
        iniciarSesion!!.setOnClickListener{
            val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion


            val consulta = "SELECT * FROM dbo.TipoUsuarios"   //Escribir la consulta
            val iniciarConexion :PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            //Preparar los parametros de la consulta si es necesario con sets
            val dataSet : ResultSet? = iniciarConexion?.executeQuery()


            while (dataSet?.next() == true){
                val tipoUsuario : String = dataSet.getString(2)
                Toast.makeText(this,tipoUsuario,Toast.LENGTH_LONG).show()
            }

            objConexion?.close()



        }



    }


}