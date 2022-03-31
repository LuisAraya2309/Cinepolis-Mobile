package com.example.cinepolismobile

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AdapterAlimentos(var mCtx: Context, var resources:Int, var listaAlimentos: List<Alimento>) : ArrayAdapter<Alimento>(mCtx,resources, listaAlimentos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = inflater.inflate(resources,null)


        val imagenAlimento = view.findViewById<ImageView>(R.id.imagen_alimento)
        val nombre = view.findViewById<TextView>(R.id.titulo_alimento)
        val precio = view.findViewById<TextView>(R.id.precio)
        val tipo = view.findViewById<TextView>(R.id.año_publicacion)

        var blob = listaAlimentos[position].imagen
        var imagenBinaria  = blob.getBytes(1, blob.length().toInt())
        var opciones = BitmapFactory.Options()
        var bitmap = BitmapFactory.decodeByteArray(imagenBinaria,0,imagenBinaria.size,opciones)

        imagenAlimento.setImageBitmap(bitmap)
        nombre.text = listaAlimentos[position].nombre
        precio.text = listaAlimentos[position].precio.toString()
        tipo.text = listaAlimentos[position].tipo


        return view
    }
}


/*


 */