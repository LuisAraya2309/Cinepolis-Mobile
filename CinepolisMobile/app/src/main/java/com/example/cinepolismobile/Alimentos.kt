package com.example.cinepolismobile

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

        carteleraAlimentos!!.adapter = AdapterAlimentos(this,R.layout.list_item_alimentos,listaAlimentos)
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
                    Alimento(dataSet.getString(1), dataSet.getString(2), dataSet.getInt(3), dataSet.getBinaryStream(4))
                }  else{
                    null
                }
            }.toList()
        }

        return listaAlimentos


    }
}