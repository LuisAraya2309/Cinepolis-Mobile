package com.example.cinepolismobile


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*


class MainActivity : AppCompatActivity() {

    private var iniciarSesion : Button ? = null
    private var registrarCuenta: Button ? = null

    private var conexionBase = ConexionBD()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asociar boton de inicio de sesion
        iniciarSesion = findViewById(R.id.iniciosesion)
        registrarCuenta = findViewById(R.id.button3)

        //Asociar boton de crear cuenta
        registrarCuenta!!.setOnClickListener{
            val registrar : Intent = Intent(this,RegistrarCuenta::class.java)
            startActivity(registrar)
        }

        //Funcion del boton de iniciar sesion
        iniciarSesion!!.setOnClickListener{

            //Instanciar el objeto conexion
            val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion

            //Obtener los campos necesarios para el procesamiento de datos con su id en XML
            val emailUsuario : String = (findViewById<View>(R.id.editTextTextEmailAddress) as EditText).text.toString()
            val passwordUsuario : String = (findViewById<View>(R.id.editTextTextPassword) as EditText).text.toString()

            //Escribir la consulta
            val consulta = "EXECUTE sp_InicioSesion ?,?"
            val iniciarConexion :PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            //Preparar los parametros de la consulta si es necesario con sets
            iniciarConexion?.setString(1,emailUsuario)
            iniciarConexion?.setString(2,passwordUsuario)
            val dataSet : ResultSet? = iniciarConexion?.executeQuery()
            var tipoUsuario: Int?
            var idUsuario : Int = 0;

            if (dataSet!!.next()){   //Si el dataset es nulo, las credenciales son incorrectas
                idUsuario = dataSet.getInt(1)
                tipoUsuario = dataSet.getInt(2)
                Toast.makeText(this,"??Bienvenido!",Toast.LENGTH_LONG).show()

                if(tipoUsuario == 2){
                    //Pasar a la siguiente interfaz
                    val dashboardPrincipal : Intent = Intent(this,Dashboardusuario::class.java)
                    dashboardPrincipal.putExtra("idUsuario",obtenerIdCliente(emailUsuario,passwordUsuario))
                    startActivity(dashboardPrincipal)
                }else{
                    val dashboardAdmin : Intent = Intent(this,Dashboardadmin::class.java)
                    startActivity(dashboardAdmin)
                }

            }
            else{
                Toast.makeText(this,"Credenciales incorrectas",Toast.LENGTH_LONG).show()
            }


            //Cerrar la conexion
            objConexion?.close()

        }
    }
    fun obtenerIdCliente(email:String,password:String):Int{
        val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion
        val consulta = "SELECT U.Id FROM dbo.Usuarios AS U WHERE U.CorreoElectronico = '$email' AND U.Contrase??a = '$password'"
        val iniciarConexion :PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)
        val dataSet : ResultSet? = iniciarConexion?.executeQuery()
        if (dataSet!!.next()){   //Si el dataset es nulo, las credenciales son incorrectas
            return  dataSet.getInt(1)

        }

        return -1

    }




}