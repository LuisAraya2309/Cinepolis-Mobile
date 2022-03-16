package com.example.cinepolismobile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.Options
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.io.InputStream
import java.lang.Exception

class AdapterPeliculas( var mCtx: Context, var resources:Int, var listaPeliculas: List<Pelicula>) : ArrayAdapter<Pelicula>(mCtx,resources, listaPeliculas) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = inflater.inflate(resources,null)

        val imagenPelicula = view.findViewById<ImageView>(R.id.imagen_pelicula)
        val titulo = view.findViewById<TextView>(R.id.titulo_pelicula)
        val director = view.findViewById<TextView>(R.id.director)
        val publicacion = view.findViewById<TextView>(R.id.año_publicacion)
        val edad_permitida = view.findViewById<TextView>(R.id.edad_permitida)
        val idioma = view.findViewById<TextView>(R.id.idioma)

        /*
        try{
            var streamBinario : InputStream? = listaPeliculas[position].imagen
             var opciones  = Options()
             opciones.inJustDecodeBounds = true

            var imagenBinaria : Bitmap
            imagenBinaria = BitmapFactory.decodeStream(streamBinario,null,opciones)!!
        }catch(e:Exception){

        }
        */
        imagenPelicula.setImageResource(R.drawable.jarvissd)
        titulo.text = listaPeliculas[position].titulo
        director.text = listaPeliculas[position].director
        publicacion.text = listaPeliculas[position].añoPublicacion
        edad_permitida.text = listaPeliculas[position].edadRequerida
        var listaIdiomas:String = listaPeliculas[position].idioma
        var idiomasParseados = listaIdiomas.split(',')
        if(idiomasParseados.size>1){
            idioma.text = "SUB"
        }
        else{
            idioma.text = "DOB"
        }


        return view
    }
}


/*


 */