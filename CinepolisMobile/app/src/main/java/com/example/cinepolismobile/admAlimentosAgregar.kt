package com.example.cinepolismobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class AdmAlimentosAgregar : AppCompatActivity(){

    private val seleccionarAct = 1
    private lateinit var agregarFoto: Button
    private lateinit var uriImagen: Uri
    private var agregarAlimentos : Button? = null
    private var conexionBase = ConexionBD()
    private lateinit var contexto: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_alimentos_agregar)

        agregarFoto = findViewById(R.id.agregarFoto)
        agregarFoto.setOnClickListener {
            ControladorImagen.seleccionarImagen(this, seleccionarAct)
        }

        agregarAlimentos = findViewById(R.id.agregarAlimento)
        agregarAlimentos!!.setOnClickListener {

            val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion

            val precio : Int = ((findViewById<View>(R.id.precio) as EditText).text.toString()).toInt()
            val cantidad : Int = ((findViewById<View>(R.id.cantidad) as EditText).text.toString()).toInt()
            val nombre : String = (findViewById<View>(R.id.idSala) as EditText).text.toString()
            val tipoAlimento: Int = (findViewById<View>(R.id.tiposAlimentos) as Spinner).selectedItemPosition + 1
            contexto = this@AdmAlimentosAgregar
            val imagen = contexto.contentResolver.openInputStream(uriImagen)?.readBytes()!!

            val consulta = "EXECUTE sp_InsertarAlimento ?,?,?,?,?"
            val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            iniciarConexion?.setString(1,nombre)
            iniciarConexion?.setInt(2,cantidad)
            iniciarConexion?.setInt(3,tipoAlimento)
            iniciarConexion?.setInt(4,precio)
            iniciarConexion?.setBytes(5,imagen)

            val dataSet : ResultSet? = iniciarConexion?.executeQuery()

            if(dataSet!!.next()){
                var outResultCode : Int?
                outResultCode = dataSet.getInt("Codigo")

                if(outResultCode==0){
                    Toast.makeText(this,"¡Producto agregado con exito!", Toast.LENGTH_LONG).show()

                    //Pasar al inicio de sesion
                    val inicioSesion : Intent = Intent(this,MainActivity::class.java)
                    startActivity(inicioSesion)
                }
                else{
                    Toast.makeText(this,"Información invalida", Toast.LENGTH_LONG).show()
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

