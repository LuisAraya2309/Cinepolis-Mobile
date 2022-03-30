package com.example.cinepolismobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*

class Seleccion : AppCompatActivity() {

    private lateinit var spinnerFila : Spinner
    private lateinit var spinnerAsiento : Spinner
    private lateinit var textoOcupados : TextView

    private lateinit var botonSeleccionar : Button
    private lateinit var botonConsultar : Button
    private lateinit var botonConfirmar : Button

    private  var listaSeleccionados : MutableList<String>? = mutableListOf("")
    private  var listaOcupadosAntes : MutableList<String>? = mutableListOf("")

    private var cantidadAsientos : Int = 0
    private var asientosEscogidos:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion)
        val idPelicula = intent.extras!!.getInt("idPelicula")
        cantidadAsientos = intent.extras!!.getInt("cantidadAsientos")
        val asientosTotalesCarrito = intent.extras!!.getInt("cantidadAsientos")
        val costoTotalAsientos : Int = intent.extras!!.getInt("costoTotal")
        val horaFuncion: String? = intent.extras!!.getString("horaFuncion")
        val letras = listOf<String>("A","B","C","D","E","F")
        val numeros = listOf<String>("1","2","3","4","5","6","7","8","9","10")
        spinnerFila = findViewById<Spinner>(R.id.fila_asiento)
        spinnerAsiento = findViewById<Spinner>(R.id.numero_asiento)
        val ocupados = obtenerAsientosOcupados(horaFuncion,idPelicula)
        textoOcupados = findViewById(R.id.asientos_seleccionados)
        textoOcupados.text = ocupados

        spinnerFila.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,letras)
        spinnerAsiento.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,numeros)

        val listaTempOcupados = ocupados.split(' ')
        for(asiento in listaTempOcupados){
            listaOcupadosAntes!!.add(asiento)
        }

        botonSeleccionar = findViewById(R.id.seleccionar_asiento)
        botonSeleccionar.setOnClickListener {
            println(cantidadAsientos)
            val asientoSeleccionado = spinnerFila.selectedItem.toString() + spinnerAsiento.selectedItem.toString()

            if(asientoSeleccionado in listaSeleccionados!! || asientoSeleccionado in listaOcupadosAntes!!){
                Toast.makeText(this,"Ese asiento ya ha sido seleccionado ", Toast.LENGTH_SHORT).show()
            }
            else{
                listaSeleccionados!!.add(asientoSeleccionado)
                asientosEscogidos+=("$asientoSeleccionado ")
                this.cantidadAsientos -= 1
                if(cantidadAsientos==0){
                    botonSeleccionar.isEnabled = false
                }
                Toast.makeText(this,"Asiento seleccionado ", Toast.LENGTH_SHORT).show()
            }

        }

        botonConsultar = findViewById(R.id.consultarAsientos)
        botonConsultar.setOnClickListener {

            var asientosEscogidos:String = ""
            for(asiento in listaSeleccionados!!){
                asientosEscogidos+=("$asiento  ")
            }
            Toast.makeText(this,asientosEscogidos, Toast.LENGTH_SHORT).show()
        }

        botonConfirmar = findViewById(R.id.confirmar)
        botonConfirmar.setOnClickListener {
            asientosEscogidos = asientosEscogidos.substring(0,asientosEscogidos.length-1)
            var nuevosAsientosOcupados = ocupados + asientosEscogidos
            nuevosAsientosOcupados = nuevosAsientosOcupados.replace("  ", "")
            val asientosParseados = nuevosAsientosOcupados.split(' ')
            var nuevosOcupados =""
            var contador = 1
            for(asiento in asientosParseados){
                val asientoEnumerado = ("$contador:$asiento,")
                contador++
                nuevosOcupados+=asientoEnumerado
            }
            nuevosOcupados = nuevosOcupados.substring(0,nuevosOcupados.length-1)

            val conexionBD = ConexionBD()
            val objConexion = conexionBD.conectarDB()
            val consulta = "UPDATE dbo.Funciones\n" +
                            "\tSET\n" +
                            "\t\tAsientosDisponibles = '$nuevosOcupados'\n" +
                            "\tWHERE IdPelicula = $idPelicula AND Hora = '$horaFuncion';"
            val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
            val dataSet  = iniciarConexion?.executeUpdate()



            val devolverDashboard : Intent = Intent(this,Dashboardusuario::class.java)
            val idCliente = intent.extras!!.getInt("idCliente")
            var infoBoletos: String = "$asientosTotalesCarrito-Boletos de "+ obtenerNombrePelicula(idPelicula) + " " + horaFuncion + " ₡$costoTotalAsientos"
            actualizarCarrito(infoBoletos, idCliente)
            devolverDashboard.putExtra("idCliente",idCliente)
            startActivity(devolverDashboard)

        }

    }

    fun actualizarCarrito(infoBoletos:String,idCliente:Int){
        val conexionBase = ConexionBD()
        val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion
        val consulta = "IF NOT EXISTS (SELECT * FROM Carritos WHERE IdCliente = $idCliente)   INSERT INTO Carritos VALUES($idCliente,'$infoBoletos')"
        val iniciarConexion : PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)
        iniciarConexion?.executeUpdate()
    }

    fun obtenerNombrePelicula(idPelicula: Int):String{
        val conexionBase = ConexionBD()
        val objConexion : Connection? = conexionBase.conectarDB()  //Crear el objeto conexion
        val consulta = "SELECT P.Titulo FROM dbo.Pelicula AS P WHERE P.Id = $idPelicula"
        val iniciarConexion :PreparedStatement? = conexionBase.prepararConsulta(objConexion,consulta)
        val dataSet = iniciarConexion?.executeQuery()
        if(dataSet!!.next()){
            return dataSet.getString(1)
        }
        return "No disponible"
    }




    fun obtenerAsientosOcupados(hora:String?,idPelicula: Int) : String{

        val conexionBD = ConexionBD()
        val objConexion = conexionBD.conectarDB()
        val consulta = "SELECT DISTINCT\n" +
                "\tF.AsientosDisponibles\n" +
                "FROM dbo.Funciones AS F\n" +
                "WHERE F.Hora = '$hora' AND F.IdPelicula = $idPelicula;"
        val iniciarConexion : PreparedStatement? = conexionBD.prepararConsulta(objConexion,consulta)
        val dataSet  = iniciarConexion?.executeQuery()
        var filas = dataSet.use {
            generateSequence {
                if (dataSet!!.next()){
                    dataSet.getString(1)
                }  else{
                    null
                }
            }.toList()
        }

        var asientosOcupados = filas[0]
        val asientosSinComas = asientosOcupados.split(',') //[1:i2, 2:i3,3:d9]
        var textoOcupados = ""

        for(par in asientosSinComas){
            val nuevoFila = par.split(':')[1][0].uppercase()
            val largoAsientos = par.split(':')[1].length
            val nuevoAsiento = par.split(':')[1].slice(1..(largoAsientos-1))
            textoOcupados+=("$nuevoFila$nuevoAsiento ")
        }
        return textoOcupados
    }

}