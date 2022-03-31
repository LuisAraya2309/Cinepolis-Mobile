package com.example.cinepolismobile

import android.content.Context
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
        val titulo = view.findViewById<TextView>(R.id.titulo_alimento)
        val director = view.findViewById<TextView>(R.id.precio)
        val publicacion = view.findViewById<TextView>(R.id.tipo)
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
        director.text = listaPeliculas[position].hora
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