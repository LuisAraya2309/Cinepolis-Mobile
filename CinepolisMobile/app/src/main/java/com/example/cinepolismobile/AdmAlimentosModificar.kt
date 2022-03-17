package com.example.cinepolismobile

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Configuration
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AdmAlimentosModificar : AppCompatActivity(){

    private val seleccionarAct = 1
    private lateinit var agregarFoto: Button
    private lateinit var uriImagen: Uri
    private var modificarAlimentos : Button? = null
    private var conexionBase = ConexionBD()
    private lateinit var contexto: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_alimentos_modificar)

        agregarFoto = findViewById(R.id.agregarFoto)
        agregarFoto.setOnClickListener {
            ControladorImagen.seleccionarImagen(this, seleccionarAct)
        }

        modificarAlimentos = findViewById(R.id.modificarAlimento)
        modificarAlimentos!!.setOnClickListener {

            val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion

            val precio : Int = ((findViewById<View>(R.id.precio) as EditText).text.toString()).toInt()
            val cantidad : Int = ((findViewById<View>(R.id.cantidad) as EditText).text.toString()).toInt()
            val nombreViejo : String = (findViewById<View>(R.id.nombre) as EditText).text.toString()
            val nombreNuevo : String = (findViewById<View>(R.id.nombreNuevo) as EditText).text.toString()
            val tipoAlimento: Int = (findViewById<View>(R.id.tiposAlimentos) as Spinner).selectedItemPosition + 1
            contexto = this@AdmAlimentosModificar
            val imagen = contexto.contentResolver.openInputStream(uriImagen)?.readBytes()!!

            val consulta = "EXECUTE sp_ModificarAlimento ?,?,?,?,?,?"
            val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            iniciarConexion?.setString(1,nombreViejo)
            iniciarConexion?.setString(2,nombreNuevo)
            iniciarConexion?.setInt(3,cantidad)
            iniciarConexion?.setInt(4,tipoAlimento)
            iniciarConexion?.setInt(5,precio)
            iniciarConexion?.setBytes(6,imagen)

            val dataSet : ResultSet? = iniciarConexion?.executeQuery()

            if(dataSet!!.next()){
                var outResultCode : Int?
                outResultCode = dataSet.getInt("Codigo")

                if(outResultCode==0){
                    Toast.makeText(this,"¡Producto modificado con éxito!", Toast.LENGTH_LONG).show()

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == seleccionarAct && resultCode == Activity.RESULT_OK ->{
                uriImagen = data!!.data!!

            }
        }
    }
}
