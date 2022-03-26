package com.example.cinepolismobile


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class AdmClientesEliminar : AppCompatActivity(){


    private var EliminarClientes : Button? = null
    private var conexionBase = ConexionBD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_clientes_eliminar)

        var objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion
        var dataSet : ResultSet?

        val spinner = findViewById<Spinner>(R.id.clientes)
        val listaClientes = obtenerClientes()
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item,listaClientes)
        spinner.adapter = adaptador

        EliminarClientes = findViewById(R.id.eliminarCliente)
        EliminarClientes!!.setOnClickListener {
            objConexion = conexionBase.conectarDB()  //Crear el objeto conexion
            val cliente: String = (findViewById<View>(R.id.clientes) as Spinner).selectedItem.toString()
            var identificacionUsuario: CharSequence? = null
            for (index in cliente.indices) {
                if (cliente[index] == '-') {
                    identificacionUsuario= cliente.subSequence(index + 2, cliente.length)
                }
            }
            val identificacion: String = identificacionUsuario.toString()
            val consulta = "EXECUTE sp_EliminarCliente ?"
            val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            iniciarConexion?.setInt(1,identificacion.toInt())
            dataSet = iniciarConexion?.executeQuery()

            if(dataSet!!.next()){
                var outResultCode : Int?
                outResultCode = dataSet!!.getInt("Codigo")

                if(outResultCode==0){
                    Toast.makeText(this,"¡Cliente eliminado con éxito!", Toast.LENGTH_LONG).show()

                    //Pasar al inicio de sesion
                    val dashbordAdmCliente : Intent = Intent(this,AdmClientes::class.java)
                    startActivity(dashbordAdmCliente)
                }
                else{
                    Toast.makeText(this,"Información inválida", Toast.LENGTH_LONG).show()
                }
            }
            //Cerrar la conexion
            objConexion?.close()
        }
    }

    fun obtenerClientes(): List<String> {
        val conexionBD = ConexionBD()
        val objConexion = conexionBD.conectarDB()
        val consulta = "EXECUTE sp_MostrarClientes "
        val iniciarConexion: PreparedStatement? = conexionBD.prepararConsulta(objConexion, consulta)
        val dataSet = iniciarConexion?.executeQuery()
        val listaClientes = dataSet.use {
            generateSequence {
                if (dataSet!!.next()) {
                    dataSet.getString("Nombre") + " " + dataSet.getString("Apellido1") + " " + dataSet.getString("Apellido2")+ " - " + dataSet.getInt("Identificacion")
                } else {
                    null
                }
            }.toList()
        }
        return listaClientes
    }
}
