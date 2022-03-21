package com.example.cinepolismobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import java.lang.Exception
import java.sql.PreparedStatement

class AdmCarteleraEliminar : AppCompatActivity() {
    private lateinit var botonEliminar : Button
    private lateinit var funciones : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_cartelera_eliminar)

        botonEliminar = findViewById(R.id.eliminarFuncion)
        val listaFunciones = obtenerListaFunciones()
        funciones = findViewById(R.id.funciones)
        funciones.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaFunciones)
        botonEliminar.setOnClickListener {
            var peliculaAEliminar = funciones.selectedItem.toString().split(',')[0]
            var horaPelicula = funciones.selectedItem.toString().split(',')[1]
            eliminarPelicula(peliculaAEliminar,horaPelicula)
        }

    }

    private fun obtenerListaFunciones():List<String>{
        val conexionBD = ConexionBD()
        val objConexion = conexionBD.conectarDB()
        val consulta = "SELECT\n" +
                "\tP.Titulo,\n" +
                "\tF.Hora\n" +
                "FROM DBO.Funciones AS F\n" +
                "INNER JOIN dbo.Pelicula AS P\n" +
                "ON F.IdPelicula = P.Id\n" +
                "WHERE F.Fecha = '2022-03-10'"   //(CONVERT(DATE,GETDATE()))
        val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
        val dataSet  = iniciarConexion?.executeQuery()
        val listaFunciones = dataSet.use {
            generateSequence {
                if (dataSet!!.next()){
                    (dataSet.getString(1) + ',' + dataSet.getString(2)).toString()
                }  else{
                    null
                }
            }.toList()
        }
        return listaFunciones
    }

    private fun obtenerIdPelicula(pPeliculaEliminar:String):Int{
        try{
            val conexionBD = ConexionBD()
            val objConexion = conexionBD.conectarDB()
            val consulta = "SELECT P.Id FROM dbo.Pelicula AS P WHERE P.Titulo = '$pPeliculaEliminar'"
            val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
            val dataSet  = iniciarConexion?.executeQuery()
            if (dataSet!!.next()){
                return dataSet.getInt(1)
            }

        }catch (e:Exception){
            e.printStackTrace()

        }
        return -1

    }


    private fun eliminarPelicula(pPeliculaEliminar:String,pHora:String){
        try{
            val conexionBD = ConexionBD()
            val objConexion = conexionBD.conectarDB()
            val idPelicula:Int = obtenerIdPelicula(pPeliculaEliminar)
            val consulta = "DELETE FROM dbo.Funciones WHERE IdPelicula = $idPelicula AND Hora = '$pHora'"
            val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
            val dataSet  = iniciarConexion?.executeUpdate()
            if(dataSet!=0){
                Toast.makeText(this,"Funcion eliminada con éxito.", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Ha habido un error en la transacción", Toast.LENGTH_SHORT).show()
            }

        }catch (e:Exception){
            e.printStackTrace()

        }

    }
}