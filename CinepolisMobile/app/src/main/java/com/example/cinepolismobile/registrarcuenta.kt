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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class RegistrarCuenta : AppCompatActivity() {

    private lateinit var tvDatePicker : TextView
    private lateinit var ivDatePicker: ImageView
    private var crearCuenta : Button? = null
    private var conexionBase = ConexionBD()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarcuenta)


        tvDatePicker = findViewById(R.id.verFecha)
        ivDatePicker = findViewById(R.id.calendario)
        crearCuenta = findViewById(R.id.button)

        val calendario = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { vista, año, mes, dia ->
            calendario.set(Calendar.YEAR,año)
            calendario.set(Calendar.MONTH,mes)
            calendario.set(Calendar.DAY_OF_MONTH,dia)
            updateLable(calendario)
        }

        ivDatePicker.setOnClickListener {
            DatePickerDialog(this, datePicker, calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)).show()
        }

        //Funcion del boton de crear cuenta
        crearCuenta!!.setOnClickListener{
            //Instanciar el objeto conexion
            val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion

            //Obtener los campos necesarios para el procesamiento de datos con su id en XML
            val emailUsuario : String = (findViewById<View>(R.id.email) as EditText).text.toString()
            val nombreUsuario : String = (findViewById<View>(R.id.nombre) as EditText).text.toString()
            val apellido1Usuario : String = (findViewById<View>(R.id.apellido1) as EditText).text.toString()
            val apellido2Usuario : String = (findViewById<View>(R.id.apellido2) as EditText).text.toString()
            val identificacionUsuario : Int = ((findViewById<View>(R.id.identificacion) as EditText).text.toString()).toInt()
            val fechaUsuario : String = (findViewById<View>(R.id.verFecha) as TextView).text.toString()
            val fechaNacimientoUsuario = LocalDate.parse(fechaUsuario, DateTimeFormatter.ISO_DATE)
            val esquemaUsuario: String = (findViewById<View>(R.id.esquemaVacunacion) as Spinner).selectedItem.toString()


            //Escribir la consulta
            val consulta = "EXECUTE sp_InicioSesion ?,?"
            val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            //Preparar los parametros de la consulta si es necesario con sets
            iniciarConexion?.setString(1,emailUsuario)
            iniciarConexion?.setString(2,passwordUsuario)
            val dataSet : ResultSet? = iniciarConexion?.executeQuery()
            var tipoUsuario: Int?
            var idUsuario : Int = 0;

            if (dataSet!!.next()){   //Si el dataset es nulo, las credenciales son incorrectas
                idUsuario = dataSet.getInt(1)
                tipoUsuario = dataSet.getInt(2)
                Toast.makeText(this,"¡Bienvenido!", Toast.LENGTH_LONG).show()

                //Pasar a la siguiente interfaz
                val dashboardPrincipal : Intent = Intent(this,RegistrarCuenta::class.java)
                startActivity(dashboardPrincipal)
            }
            else{
                Toast.makeText(this,"Credenciales incorrectas", Toast.LENGTH_LONG).show()
            }

            //Cerrar la conexion
            objConexion?.close()
        }

    }

    private fun updateLable( calendario : Calendar){
        val formato = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(formato,Locale.US)
        tvDatePicker.setText(sdf.format(calendario.time))
    }

}
