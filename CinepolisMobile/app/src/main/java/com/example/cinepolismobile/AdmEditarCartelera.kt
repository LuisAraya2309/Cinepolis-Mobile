package com.example.cinepolismobile

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.lang.Exception
import java.sql.PreparedStatement
import java.text.SimpleDateFormat
import java.util.*

class AdmEditarCartelera : AppCompatActivity() {
    private lateinit var funciones : Spinner
    private lateinit var ivSeleccionarFecha: ImageView
    private lateinit var tvSeleccionarFecha : TextView
    private lateinit var botonEditarFuncion : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adm_editar_cartelera)
        tvSeleccionarFecha = findViewById(R.id.verFecha)
        ivSeleccionarFecha = findViewById(R.id.calendario)
        val listaFunciones = obtenerListaFunciones()
        funciones = findViewById(R.id.funcioneseditar)
        funciones.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,listaFunciones)

        val calendario = Calendar.getInstance()
        val seleccionarFecha = DatePickerDialog.OnDateSetListener { vista, año, mes, dia ->
            calendario.set(Calendar.YEAR,año)
            calendario.set(Calendar.MONTH,mes)
            calendario.set(Calendar.DAY_OF_MONTH,dia)
            updateLable(calendario)
        }
        ivSeleccionarFecha.setOnClickListener {
            DatePickerDialog(this, seleccionarFecha, calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)).show()
        }


        botonEditarFuncion = findViewById(R.id.editarCartelera)
        botonEditarFuncion.setOnClickListener {
            var peliculaAEditar = funciones.selectedItem.toString().split(',')[0]
            var horaPelicula = funciones.selectedItem.toString().split(',')[1]
            val idFuncion = obtenerIdFuncion(peliculaAEditar,horaPelicula)
            val idSala = (findViewById<EditText>(R.id.idSala) as EditText).text.toString().toInt()
            val idPelicula = (findViewById<EditText>(R.id.idPelicula) as EditText).text.toString().toInt()
            val hora = (findViewById<EditText>(R.id.horaFuncion) as EditText).text.toString()
            val asientosOcupados = (findViewById<EditText>(R.id.asientosOcupados) as EditText).text.toString()
            val fecha = (findViewById<TextView>(R.id.verFecha) as TextView).text.toString()
            try{
                val conexionBD = ConexionBD()
                val objConexion = conexionBD.conectarDB()
                val consulta = "UPDATE Funciones\n" +
                        "\tSET \n" +
                        "\t\tIdSala = $idSala,\n" +
                        "\t\tIdPelicula = $idPelicula,\n" +
                        "\t\tFecha = '$fecha',\n" +
                        "\t\tHora = '$hora',\n" +
                        "\t\tAsientosDisponibles ='$asientosOcupados'\n" +
                        "\tWHERE Id = $idFuncion"
                val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
                val result  = iniciarConexion?.executeUpdate()
                if(result==1){
                    Toast.makeText(this,"Editado con éxito", Toast.LENGTH_LONG).show()
                }
                else{

                    Toast.makeText(this,"Información incorrecta.", Toast.LENGTH_LONG).show()
                }
            }catch(e: Exception){
                e.printStackTrace()
                Toast.makeText(this,"Formato de información incorrecto.", Toast.LENGTH_LONG).show()
            }
        }





    }

    private fun updateLable( calendario : Calendar){
        val formato = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(formato, Locale.US)
        tvSeleccionarFecha.setText(sdf.format(calendario.time))
    }

    private fun obtenerListaFunciones():List<String>{
        val conexionBD = ConexionBD()
        val objConexion = conexionBD.conectarDB()
        val consulta = "SELECT\n" +
                "\tP.Titulo,\n" +
                "\tF.Hora\n" +
                "FROM DBO.Funciones AS F\n" +
                "INNER JOIN dbo.Pelicula AS P\n" +
                "ON F.IdPelicula = P.Id\n" +
                "WHERE F.Fecha = '2022-03-10'"   //(CONVERT(DATE,GETDATE()))
        val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
        val dataSet  = iniciarConexion?.executeQuery()
        val listaFunciones = dataSet.use {
            generateSequence {
                if (dataSet!!.next()){
                    (dataSet.getString(1) + ',' + dataSet.getString(2)).toString()
                }  else{
                    null
                }
            }.toList()
        }
        return listaFunciones
    }
    private fun obtenerIdFuncion(pPeliculaEliminar:String,pHora:String):Int{
        try{
            val conexionBD = ConexionBD()
            val objConexion = conexionBD.conectarDB()
            val consulta = "SELECT TOP (1) F.Id FROM dbo.Funciones AS F\n" +
                    "INNER JOIN dbo.Pelicula AS P\n" +
                    "ON P.Titulo = '$pPeliculaEliminar' AND F.Hora = '$pHora' "
            val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
            val dataSet  = iniciarConexion?.executeQuery()
            if (dataSet!!.next()){
                return dataSet.getInt(1)
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
        return -1

    }


}