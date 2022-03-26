package com.example.cinepolismobile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.stream.IntStream.range

class VentanaEmergente : AppCompatActivity(){

    private var salir : TextView? = null
    private var sumar : TextView? = null
    private var restar : TextView? = null
    private var aceptar : TextView? = null
    private var conexionBase = ConexionBD()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_emergente)

        val medidasVentana = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(medidasVentana)

        val ancho : Int = medidasVentana.widthPixels
        val alto : Int = medidasVentana.heightPixels

        window.setLayout((ancho.toDouble() * 0.70).toInt(), (alto.toDouble() * 0.25).toInt())
        val cantidad : TextView = findViewById(R.id.cantidadProductos)
        val idUsuario = intent.extras!!.getInt("idUsuario")
        val listaProductos = obtenerProductos(idUsuario)
        val cantidadProducto = intent.extras!!.getString("cantProducto").toString()
        cantidad.text = cantidadProducto

        salir = findViewById(R.id.salir)
        salir!!.setOnClickListener{
            val registrar : Intent = Intent(this,CarritoCliente::class.java)
            startActivity(registrar)
        }

        sumar = findViewById(R.id.sumar)
        sumar!!.setOnClickListener{
            val cantProducto : Int = cantidad.text.toString().toInt()
            cantidad.text = (cantProducto + 1).toString()
        }

        restar = findViewById(R.id.restar)
        restar!!.setOnClickListener{
            val cantProducto : Int = cantidad.text.toString().toInt()
            if(cantProducto!=0){
                cantidad.text = (cantProducto - 1).toString()
            }else{
                Toast.makeText(this,"Ya elimino el producto de su carrito", Toast.LENGTH_LONG).show()
            }
        }
        aceptar = findViewById(R.id.aceptar)
        aceptar!!.setOnClickListener{

            val objConexion : Connection? = conexionBase.conectarDB()
            val nuevaCantidad : Int = cantidad.text.toString().toInt()
            val cambiar = intent.extras!!.getString("producto").toString()

            val nuevaLista: String = crearCarrito(cambiar,nuevaCantidad,listaProductos)

            val consulta = "EXECUTE sp_ActualizarCarrito ?,?"
            val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            //Preparar los parametros de la consulta si es necesario con sets
            iniciarConexion?.setInt(1,idUsuario)
            iniciarConexion?.setString(2,nuevaLista)
            val dataSet : ResultSet? = iniciarConexion?.executeQuery()

            if(dataSet!!.next()){
                var outResultCode : Int?
                outResultCode = dataSet.getInt("Codigo")

                if(outResultCode==0){
                    Toast.makeText(this,"¡Gracias por su compra ,la factura llegara a su correo electrónico!", Toast.LENGTH_LONG).show()

                    //Pasar al inicio de sesion
                    val carrito : Intent = Intent(this,CarritoCliente::class.java)
                    carrito.putExtra("idUsuario",idUsuario)
                    startActivity(carrito)
                }
                else{
                    Toast.makeText(this,"Error al procesar la compra", Toast.LENGTH_LONG).show()
                }
            }
            //Cerrar la conexion
            objConexion?.close()
        }
    }
    fun obtenerProductos(idUsuario:Int):List<String> {
        val conexionBD = ConexionBD()
        val objConexion = conexionBD.conectarDB()
        val consulta = "SELECT DISTINCT \n" +
                "\tC.Productos\n" +
                "FROM dbo.Carritos AS C\n" +
                "WHERE C.IdCliente = " + idUsuario
        val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
        val dataSet  = iniciarConexion?.executeQuery()
        var carrito: String
        var listaProductos: List<String>
        if (dataSet!!.next()){
            carrito =  dataSet.getString(1)
            listaProductos = carrito.split(",")
        }
        else{
            listaProductos = emptyList()
            null
        }
        return listaProductos
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun crearCarrito(cambiar: String, cantidad: Int, listaProductos: List<String> ):String{
        var listaFinal  : String = ""
        var cambiado : List<String>

        for(indice in range(0,listaProductos.size)){
            if(indice == 0){
                if(listaProductos[indice] != cambiar){
                    listaFinal = listaProductos[indice]
                }else{
                    cambiado = cambiar.split("-")
                    listaFinal = (cantidad.toString()+ "-" + cambiado[1])
                }
            }
            else{
                if(listaProductos[indice] != cambiar){
                    listaFinal += ("," + listaProductos[indice])
                }else{
                    cambiado = cambiar.split("-")
                    listaFinal += ("," + cantidad.toString()+ "-" + cambiado[1])
                }
            }
        }
        return listaFinal
    }
}
