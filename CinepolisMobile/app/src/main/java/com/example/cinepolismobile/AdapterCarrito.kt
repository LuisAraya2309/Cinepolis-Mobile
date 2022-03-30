package com.example.cinepolismobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.sql.PreparedStatement
import java.sql.ResultSet


class AdapterCarrito( var mCtx: Context, var resources:Int, var listaCarrito: List<String>) : ArrayAdapter<String>(mCtx,resources, listaCarrito) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = inflater.inflate(resources,null)

        val titulo = view.findViewById<TextView>(R.id.titulo_producto)
        val infoProducto: List<String> = listaCarrito[position].split("-")
        if("Boleto" in listaCarrito[position]){
            titulo.text = listaCarrito[position]
        }else{
            titulo.text = listaCarrito[position] + " ¢"+obtenerPrecio(infoProducto[1], infoProducto[0].toInt())
        }
        return view
    }
}

fun obtenerPrecio(nombreProducto:String,cantidadProducto:Int):Int{

    val conexionBD = ConexionBD()
    val objConexion = conexionBD.conectarDB()

    val consulta = "EXECUTE sp_ObtenerPrecioProducto ?"
    val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)

    iniciarConexion?.setString(1,nombreProducto)
    val dataSet : ResultSet? = iniciarConexion?.executeQuery()

    return if (dataSet!!.next()){
        dataSet.getInt(1) * cantidadProducto
    }else{
        0
    }
    objConexion?.close()
}


