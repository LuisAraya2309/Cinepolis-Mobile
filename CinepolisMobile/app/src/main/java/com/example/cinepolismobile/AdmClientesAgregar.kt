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


class AdmClientesAgregar : AppCompatActivity() {

    private lateinit var tvSeleccionarFecha : TextView
    private lateinit var ivSeleccionarFecha: ImageView
    private var crearCuenta : Button? = null
    private var conexionBase = ConexionBD()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_clientes_agregar)


        tvSeleccionarFecha = findViewById(R.id.verFecha)
        ivSeleccionarFecha = findViewById(R.id.calendario)
        crearCuenta = findViewById(R.id.agregarCliente)

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
            val correoUsuario : String = (findViewById<View>(R.id.asientosOcupados) as EditText).text.toString()
            val nombreUsuario : String = (findViewById<View>(R.id.idSala) as EditText).text.toString()
            val apellido1Usuario : String = (findViewById<View>(R.id.idPelicula) as EditText).text.toString()
            val apellido2Usuario : String = (findViewById<View>(R.id.horaFuncion) as EditText).text.toString()
            val identificacionUsuario : Int = ((findViewById<View>(R.id.identificacion) as EditText).text.toString()).toInt()
            val fechaUsuario : String = (findViewById<View>(R.id.verFecha) as TextView).text.toString()
            val esquemaUsuario: String = (findViewById<View>(R.id.esquemaVacunacion) as Spinner).selectedItem.toString()
            println(fechaUsuario)

            //Funcion para contraseña aleatoria
            var contraseña: String  = ""

            fun crearContraseña(pContraseña: String ) : String {
                val alfabeto = 'a'..'z'
                val numeros  = 1..9
                while(pContraseña.length.equals(7)){
                    if(numeros.random()<5){
                        pContraseña + numeros.random()
                    }else{
                        pContraseña + alfabeto.random()
                    }
                }
                return pContraseña
            }
            val contraseñaUsuario : String = crearContraseña(contraseña)

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
                    Toast.makeText(this,"¡Cuenta creada con exito!", Toast.LENGTH_LONG).show()

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
}

