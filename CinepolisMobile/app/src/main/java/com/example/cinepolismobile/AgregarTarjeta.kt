package com.example.cinepolismobile

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.text.SimpleDateFormat
import java.util.*

class AgregarTarjeta : AppCompatActivity() {

    private lateinit var tvSeleccionarFecha : TextView
    private lateinit var ivSeleccionarFecha: ImageView
    private var agregarTarjeta: Button? = null
    private var conexionBase = ConexionBD()
    private var regex = Regex( """^(([A-Z']{1,}\.?)*(\s[A-Z]{1,}\.?)*-?([A-Z']{2,})?)""")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregartarjeta)

        tvSeleccionarFecha = findViewById(R.id.verFecha)
        ivSeleccionarFecha = findViewById(R.id.calendario)
        agregarTarjeta = findViewById(R.id.agregarTarjeta)

        val calendario = Calendar.getInstance()

        val seleccionarFecha = DatePickerDialog.OnDateSetListener { vista, año, mes, dia ->
            calendario.set(Calendar.YEAR,año)
            calendario.set(Calendar.MONTH,mes)
            calendario.set(Calendar.DAY_OF_MONTH,dia)
            updateLable(calendario)
        }

        ivSeleccionarFecha.setOnClickListener {
            DatePickerDialog(this, seleccionarFecha, calendario.get(Calendar.YEAR),calendario.get(
                Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)).show()
        }

        //Funcion del boton agregar
        agregarTarjeta!!.setOnClickListener{

            //Instanciar el objeto conexion
            val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion

            //Obtener los campos necesarios para el procesamiento de datos con su id en XML
            val titular : String = (findViewById<View>(R.id.idSala) as EditText).text.toString()
            val numeroTarjeta : String = (findViewById<View>(R.id.numeroTarjeta) as EditText).text.toString()
            val codigoSeguridad : Int = ((findViewById<View>(R.id.codigoSeguridad) as EditText).text.toString()).toInt()
            val fechaVencimiento : String = (findViewById<View>(R.id.verFecha) as TextView).text.toString()
            val idCliente : Int = 3
            println(fechaVencimiento)
            if(regex.matches(titular)){

                //Escribir la consulta
                val consulta = "EXECUTE sp_AgregarTarjeta ?,?,?,?,?,?"
                val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

                //Preparar los parametros de la consulta si es necesario con sets
                iniciarConexion?.setString(1,titular)
                iniciarConexion?.setString(2,numeroTarjeta)
                iniciarConexion?.setInt(3,codigoSeguridad)
                iniciarConexion?.setString(4,fechaVencimiento)
                iniciarConexion?.setInt(5,idCliente)
                iniciarConexion?.setInt(6,0)

                val dataSet : ResultSet? = iniciarConexion?.executeQuery()

                if(dataSet!!.next()){

                    var outResultCode : Int?
                    outResultCode = dataSet.getInt("OutResultCode")

                    if(outResultCode==0){
                        Toast.makeText(this,"¡Metódo de pago agregado!", Toast.LENGTH_LONG).show()

                        //Pasar al inicio de sesion
                        val dashbordusuario : Intent = Intent(this,Dashboardusuario::class.java)
                        startActivity(dashbordusuario)
                    }
                    else{
                        Toast.makeText(this,"Información invalida, vuelva a intentarlo", Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this,"Información invalida, favor seguir el ejemplo", Toast.LENGTH_LONG).show()
            }
            //Cerrar la conexion
            objConexion?.close()
        }
    }

    private fun updateLable( calendario : Calendar){
        val formato = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(formato, Locale.US)
        tvSeleccionarFecha.setText(sdf.format(calendario.time))
    }
}