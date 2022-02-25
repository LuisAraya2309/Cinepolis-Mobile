package com.example.cinepolismobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.sql.PreparedStatement

class MainActivity : AppCompatActivity() {

    var iniciarSesion : Button ? = null;
    var conexionBase = conexionBD()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asociar boton de inicio de sesion
        iniciarSesion = findViewById(R.id.iniciosesion);

        //Funcion del boton de iniciar sesion
        iniciarSesion!!.setOnClickListener{
            var objConexion = conexionBase.conectarDB()  //Crear el objeto conexion
            var consulta = "";                           //EScribir la consulta
            //
            var iniciarConexion :PreparedStatement = conexionBase.prepararConsulta(objConexion,consulta)
            //Preparar los parametros de la consulta


            Toast.makeText(this,"Iniciando Sesión",Toast.LENGTH_LONG).show();
        }



    }


}