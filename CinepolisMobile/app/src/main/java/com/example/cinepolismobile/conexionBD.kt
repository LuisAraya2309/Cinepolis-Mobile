package com.example.cinepolismobile

import android.os.StrictMode
import android.util.Log
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException



class ConexionBD {
    //Atributo de tipo Conexion
    val ip = "10.0.2.2"
    val database = "CinepolisBD"
    val username = "sa"
    val password = "admin"

    fun conectarDB():Connection?{
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conexion : Connection? = null
        val stringConexion : String
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            stringConexion = "jdbc:jtds:sqlserver://$ip;databaseName=$database;user=$username;password=$password"
            conexion = DriverManager.getConnection(stringConexion)

        }catch (ex:SQLException){
            Log.e("Error1: ",ex.message!!)
        }catch (ex1: ClassNotFoundException){
            Log.e("Error2: ",ex1.message!!)
        }catch (ex2: Exception){
            Log.e("Error3: ",ex2.message!!)
        }
        return conexion
    }

    fun prepararConsulta(conexionBase : Connection?, consulta:String):PreparedStatement?{
        return conexionBase?.prepareStatement(consulta)
    }

}