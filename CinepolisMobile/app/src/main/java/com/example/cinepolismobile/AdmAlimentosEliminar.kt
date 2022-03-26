package com.example.cinepolismobile


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class AdmAlimentosEliminar : AppCompatActivity(){


    private var EliminarAlimentos : Button? = null
    private var conexionBase = ConexionBD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_alimentos_eliminar)

        var objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion
        var dataSet : ResultSet?

        val consulta = "EXECUTE sp_MostrarAlimentos"
        var iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

        dataSet = iniciarConexion?.executeQuery()

        val spinner = findViewById<Spinner>(R.id.alimentos)

        val listaAlimentos = dataSet.use {
            generateSequence {
                if (dataSet!!.next()){
                   dataSet!!.getString("Nombre")
                }  else{
                    null
                }
            }.toList()
        }

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item,listaAlimentos)
        spinner.adapter = adaptador

        EliminarAlimentos = findViewById(R.id.eliminarAlimento)
        EliminarAlimentos!!.setOnClickListener {
            objConexion = conexionBase.conectarDB()  //Crear el objeto conexion
            val alimento: String = (findViewById<View>(R.id.alimentos) as Spinner).selectedItem.toString()

            val consulta = "EXECUTE sp_EliminarAlimento ?"
            iniciarConexion = conexionBase.prepararConsulta(objConexion,consulta)
            iniciarConexion?.setString(1,alimento)
            dataSet = iniciarConexion?.executeQuery()

            if(dataSet!!.next()){
                var outResultCode : Int?
                outResultCode = dataSet!!.getInt("Codigo")

                if(outResultCode==0){
                    Toast.makeText(this,"¡Producto eliminado con éxito!", Toast.LENGTH_LONG).show()

                    //Pasar al inicio de sesion
                    val inicioSesion : Intent = Intent(this,MainActivity::class.java)
                    startActivity(inicioSesion)
                }
                else{
                    Toast.makeText(this,"Información inválida", Toast.LENGTH_LONG).show()
                }
            }
            //Cerrar la conexion
            objConexion?.close()
        }
    }
}
