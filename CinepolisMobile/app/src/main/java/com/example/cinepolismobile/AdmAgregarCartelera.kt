package com.example.cinepolismobile

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.lang.Exception
import java.sql.PreparedStatement
import java.text.SimpleDateFormat
import java.util.*

class AdmAgregarCartelera : AppCompatActivity() {


    private lateinit var ivSeleccionarFecha: ImageView
    private lateinit var tvSeleccionarFecha : TextView
    private lateinit var botonAgregarCartelera : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_agregar_cartelera)
        tvSeleccionarFecha = findViewById(R.id.verFecha)
        ivSeleccionarFecha = findViewById(R.id.calendario)
        botonAgregarCartelera = findViewById(R.id.editarCartelera)
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
        botonAgregarCartelera.setOnClickListener {
            val idSala = (findViewById<EditText>(R.id.inTituloBuscar) as EditText).text.toString().toInt()
            val idPelicula = (findViewById<EditText>(R.id.idPelicula) as EditText).text.toString().toInt()
            val hora = (findViewById<EditText>(R.id.horaFuncion) as EditText).text.toString()
            val asientosOcupados = (findViewById<EditText>(R.id.asientosOcupados) as EditText).text.toString()
            val fecha = (findViewById<TextView>(R.id.verFecha) as TextView).text.toString()
            try{
                val conexionBD = ConexionBD()
                val objConexion = conexionBD.conectarDB()
                val consulta = "INSERT INTO dbo.Funciones VALUES($idSala,$idPelicula,'$fecha','$hora','$asientosOcupados')"
                val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
                val result  = iniciarConexion?.executeUpdate()
                if(result==1){
                    Toast.makeText(this,"Agregado con éxito", Toast.LENGTH_LONG).show()
                }
                else{

                    Toast.makeText(this,"Información incorrecta.", Toast.LENGTH_LONG).show()
                }
            }catch(e:Exception){
                e.printStackTrace()
                Toast.makeText(this,"Formato de información incorrecto.", Toast.LENGTH_LONG).show()
            }
        }



    }


    private fun updateLable( calendario : Calendar){
        val formato = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(formato, Locale.US)
        tvSeleccionarFecha.setText(sdf.format(calendario.time))
    }
}