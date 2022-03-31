package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import java.sql.PreparedStatement

class Alimentos : AppCompatActivity() {
    private var carteleraAlimentos : ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alimentos)

        carteleraAlimentos  = findViewById<ListView>(R.id.carteleraAlimentos)
        val listaAlimentos = obtenerAlimentos()

        val idUsuario = intent.extras!!.getInt("idUsuario")

        carteleraAlimentos!!.adapter = AdapterAlimentos(this,R.layout.list_item_alimentos,listaAlimentos)

        carteleraAlimentos!!.setOnItemClickListener{parent,view,position,id ->
            val alimentoSelec = listaAlimentos[position].nombre // Esta entrando una pelicula o un string
            var tipo:String = listaAlimentos[position].tipo
            val precio : Int = listaAlimentos[position].precio

            val act : Intent = Intent(this,AgregarAlimentoCarrito::class.java)
            act.putExtra("idUsuario",idUsuario)
            startActivity(act)
        }

    }


    fun obtenerAlimentos(): List<Alimento> {
        val conexionBD = ConexionBD()
        val objConexion = conexionBD.conectarDB()
        val consulta = "EXECUTE sp_ConsultarAlimentos"
        val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
        val dataSet  = iniciarConexion?.executeQuery()
        val listaAlimentos = dataSet.use {
            generateSequence {
                if (dataSet!!.next()){
                    Alimento(dataSet.getString(1), dataSet.getString(2), dataSet.getInt(3), dataSet.getBlob(4))
                }  else{
                    null
                }
            }.toList()
        }

        return listaAlimentos


    }
}