package com.example.cinepolismobile

import android.app.DatePickerDialog
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
import java.text.SimpleDateFormat
import java.util.*


class RegistrarCuenta : AppCompatActivity() {

    private lateinit var tvSeleccionarFecha : TextView
    private lateinit var ivSeleccionarFecha: ImageView
    private var crearCuenta : Button? = null
    private var conexionBase = ConexionBD()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarcuenta)


        tvSeleccionarFecha = findViewById(R.id.verFecha)
        ivSeleccionarFecha = findViewById(R.id.calendario)
        crearCuenta = findViewById(R.id.crearCuenta)

        val calendario = Calendar.getInstance()

        val seleccionarFecha = DatePickerDialog.OnDateSetListener { vista, año, mes, dia ->
            calendario.set(Calendar.YEAR,año)
            calendario.set(Calendar.MONTH,mes)
            calendario.set(Calendar.DAY_OF_MONTH,dia)
            updateLable(calendario)
        }

        ivSeleccionarFecha.setOnClickListener {
            DatePickerDialog(this, seleccionarFecha, calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)).show()
        }

        //Funcion del boton de crear cuenta
        crearCuenta!!.setOnClickListener{
            //Instanciar el objeto conexion
            val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion

            //Obtener los campos necesarios para el procesamiento de datos con su id en XML
            val contraseñaUsuario : String = crearContraseña()
            val correoUsuario : String = (findViewById<View>(R.id.asientosOcupados) as EditText).text.toString()
            val nombreUsuario : String = (findViewById<View>(R.id.inTituloBuscar) as EditText).text.toString()
            val apellido1Usuario : String = (findViewById<View>(R.id.idPelicula) as EditText).text.toString()
            val apellido2Usuario : String = (findViewById<View>(R.id.horaFuncion) as EditText).text.toString()
            val identificacionUsuario : Int = ((findViewById<View>(R.id.identificacion) as EditText).text.toString()).toInt()
            val fechaUsuario : String = (findViewById<View>(R.id.verFecha) as TextView).text.toString()
            val esquemaUsuario: String = (findViewById<View>(R.id.esquemaVacunacion) as Spinner).selectedItem.toString()

            //Escribir la consulta
            val consulta = "EXECUTE sp_CrearCuenta ?,?,?,?,?,?,?,?,?"
            val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            //Preparar los parametros de la consulta si es necesario con sets
            iniciarConexion?.setString(1,nombreUsuario)
            iniciarConexion?.setString(2,apellido1Usuario)
            iniciarConexion?.setString(3,apellido2Usuario)
            iniciarConexion?.setString(4,correoUsuario)
            iniciarConexion?.setInt(5,identificacionUsuario)
            iniciarConexion?.setString(6,contraseñaUsuario)
            iniciarConexion?.setString(7,fechaUsuario)
            iniciarConexion?.setString(8,esquemaUsuario)
            iniciarConexion?.setInt(9,0)
            val dataSet : ResultSet? = iniciarConexion?.executeQuery()

            if(dataSet!!.next()){
                var outResultCode : Int?
                outResultCode = dataSet.getInt("OutResultCode")

                if(outResultCode==0){
                    Toast.makeText(this,"¡Cuenta creada con éxito, su contraseña se enviara al correo electrónico!", Toast.LENGTH_LONG).show()

                    var enviarCorreo =  EnviarContraseña(this,correoUsuario,"Contraseña usuario","$nombreUsuario $apellido1Usuario $apellido2Usuario \ncontraseñaUsuario")
                    enviarCorreo.execute()

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

    private fun updateLable( calendario : Calendar){
        val formato = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(formato,Locale.US)
        tvSeleccionarFecha.setText(sdf.format(calendario.time))
    }

    fun crearContraseña() : String {
        var contraseña = "" ; val alfabeto = 'a'..'z'
        val numeros  = 1..9 ; var indice = 0 ; val tamaño: IntRange = 7..11
        while(indice <= tamaño.random()){
            if(numeros.random()<5){
                contraseña += numeros.random()
            }else{
                contraseña += alfabeto.random()
            }
            indice++
        }
        return contraseña
    }

}

