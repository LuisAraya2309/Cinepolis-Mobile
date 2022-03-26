package com.example.cinepolismobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class AdapterCarrito( var mCtx: Context, var resources:Int, var listaCarrito: List<String>) : ArrayAdapter<String>(mCtx,resources, listaCarrito) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = inflater.inflate(resources,null)

        val titulo = view.findViewById<TextView>(R.id.titulo_producto)
        println(position)
        println(listaCarrito[position])
        titulo.text = listaCarrito[position]
        return view
    }
}
