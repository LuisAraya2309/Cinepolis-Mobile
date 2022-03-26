package com.example.cinepolismobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ContextUtils.getActivity
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CarritoCliente : AppCompatActivity() {

    private var modificarCant : ListView? = null
    private var finalizarCompra : Button? = null
    private var conexionBase = ConexionBD()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        modificarCant  = findViewById(R.id.listaProductos)
        val idUsuario = intent.extras!!.getInt("idUsuario")

        val listaProductos = obtenerProductos(idUsuario)

        modificarCant!!.adapter = AdapterCarrito(this,R.layout.list_item_carrito,listaProductos)

        modificarCant!!.setOnItemClickListener{parent,view,position,id ->

            val productoSeleccionada = listaProductos[position]
            val cantProducto = productoSeleccionada.split("-")[0]

            val pasarModificarCant : Intent = Intent(this,VentanaEmergente::class.java)
            pasarModificarCant.putExtra("producto",productoSeleccionada)
            pasarModificarCant.putExtra("cantProducto",cantProducto)
            pasarModificarCant.putExtra("idUsuario",idUsuario)
            startActivity(pasarModificarCant)
        }

        finalizarCompra = findViewById(R.id.comprar)
        finalizarCompra!!.setOnClickListener {

            val objConexion : Connection? = conexionBase.conectarDB()

            val consulta = "EXECUTE sp_EliminarCarrito ?"
            val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)

            //Preparar los parametros de la consulta si es necesario con sets
            iniciarConexion?.setInt(1,idUsuario)
            val dataSet : ResultSet? = iniciarConexion?.executeQuery()

            if(dataSet!!.next()){
                var outResultCode : Int?
                outResultCode = dataSet.getInt("Codigo")

                if(outResultCode==0){
                    Toast.makeText(this,"¡Gracias por su compra ,la factura llegara a su correo electrónico!", Toast.LENGTH_LONG).show()

                    sendMail("sebascout01182@gmail.com", listaProductos[1], "Factura electrónica")

                    val dashbord : Intent = Intent(this,Dashboardusuario::class.java)
                    dashbord.putExtra("idUsuario",idUsuario)
                    startActivity(dashbord)
                }
                else{
                    Toast.makeText(this,"Error al procesar la compra", Toast.LENGTH_LONG).show()
                }
            }
            //Cerrar la conexion
            objConexion?.close()
        }
    }

    fun obtenerProductos(idUsuario: Int):List<String> {

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

    fun obtenerEmail(idUsuario: Int): String {

        val conexionBD = ConexionBD()
        val objConexion = conexionBD.conectarDB()
        val consulta = "SELECT DISTINCT \n" +
                "\tU.CorreoElectronico\n" +
                "FROM dbo.Usuarios AS U\n" +
                "WHERE U.Id = " + idUsuario
        val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
        val dataSet  = iniciarConexion?.executeQuery()

        return if(dataSet!!.next()){
            dataSet.getString(1)
        } else{
            ""
        }
    }

    fun sendMail(correo:String, productos: String, asunto: String){

        val javaMailAPI = JavaMailApi(this,correo,asunto,productos)
        javaMailAPI.execute()
    }
}
