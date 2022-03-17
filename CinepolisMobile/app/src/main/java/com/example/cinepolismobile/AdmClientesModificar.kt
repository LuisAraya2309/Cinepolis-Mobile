package com.example.cinepolismobile


import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class AdmClientesModificar : AppCompatActivity() {

    private var modificarCliente : Button? = null
    private var conexionBase = ConexionBD()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_clientes_modificar)

        val spinner = findViewById<Spinner>(R.id.infoClientes)
        val listaClientes = obtenerClientes()
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item,listaClientes)
        spinner.adapter = adaptador

        modificarCliente = findViewById(R.id.modificarCliente)

        //Funcion del boton de modificar cliente
        modificarCliente!!.setOnClickListener{
            //Instanciar el objeto conexion
            val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion

            //Obtener los campos necesarios para el procesamiento de datos con su id en XML
            val infoUsuario : String = (findViewById<View>(R.id.infoClientes) as Spinner).selectedItem.toString()
            var identificacionUsuario: CharSequence? = null
            for (index in infoUsuario.indices) {
                if (infoUsuario[index] == '-') {
                    identificacionUsuario= infoUsuario.subSequence(index + 2, infoUsuario.length)
                }
            }

            val correoUsuario : String = (findViewById<View>(R.id.correoElectronico) as EditText).text.toString()
            val nombreUsuario : String = (findViewById<View>(R.id.nombre) as EditText).text.toString()
            val apellido1Usuario : String = (findViewById<View>(R.id.apellido1) as EditText).text.toString()
            val apellido2Usuario : String = (findViewById<View>(R.id.apellido2) as EditText).text.toString()

            val identificacion: String = identificacionUsuario.toString()
            val esquemaUsuario: String = (findViewById<View>(R.id.esquemaVacunacion) as Spinner).selectedItem.toString()
            val contraseñaUsuario: String = (findViewById<View>(R.id.contraseña) as EditText).text.toString()

            //Escribir la consulta
            val consulta = "EXECUTE sp_ModificarCliente ?,?,?,?,?,?,?"
            val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            //Preparar los parametros de la consulta si es necesario con sets
            iniciarConexion?.setInt(1,identificacion.toInt())
            iniciarConexion?.setString(2,nombreUsuario)
            iniciarConexion?.setString(3,apellido1Usuario)
            iniciarConexion?.setString(4,apellido2Usuario)
            iniciarConexion?.setString(5,correoUsuario)
            iniciarConexion?.setString(6,contraseñaUsuario)
            iniciarConexion?.setString(7,esquemaUsuario)
            val dataSet : ResultSet? = iniciarConexion?.executeQuery()

            if(dataSet!!.next()){
                var outResultCode : Int?
                outResultCode = dataSet.getInt("Codigo")

                if(outResultCode==0){
                    Toast.makeText(this,"¡Cliente modificado con exito!", Toast.LENGTH_LONG).show()

                    //Pasar al inicio de sesion
                    val inicioSesion : Intent = Intent(this,MainActivity::class.java)
                    startActivity(inicioSesion)
                }
                else{
                    Toast.makeText(this,"Su información ya ha sido registrada anteriormente", Toast.LENGTH_LONG).show()
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

