package com.example.cinepolismobile

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.get
import com.google.android.material.internal.ContextUtils.getActivity
import com.itextpdf.text.Chunk
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*


class CarritoCliente : AppCompatActivity() {

    private var modificarCant : ListView? = null
    private var finalizarCompra : Button? = null
    private var conexionBase = ConexionBD()

    private val storageCode = 1001

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        modificarCant  = findViewById(R.id.listaProductos)

        val lista : ListView = findViewById(R.id.listaProductos)
        val precioTotal : TextView = findViewById(R.id.precioTotal)

        val idUsuario = intent.extras!!.getInt("idUsuario")
        val listaProductos : List<String> = obtenerProductos(idUsuario)
        val montoFinal : String = (obtenerPrecioComida(listaProductos) + obtenerPrecioBoletos(listaProductos)).toString()
        precioTotal.text = montoFinal
        val data = obtenerInfoPdf(listaProductos)

        modificarCant!!.adapter = AdapterCarrito(this,R.layout.list_item_carrito,listaProductos)

        modificarCant!!.setOnItemClickListener{parent,view,position,id ->
            val productoSeleccionada = listaProductos[position]

            if("Boleto" in productoSeleccionada){
                lista[position].isEnabled = false
            }
            if(lista[position].isEnabled){

                val cantProducto = productoSeleccionada.split("-")[0]

                val pasarModificarCant : Intent = Intent(this,VentanaEmergente::class.java)
                pasarModificarCant.putExtra("producto",productoSeleccionada)
                pasarModificarCant.putExtra("cantProducto",cantProducto)
                pasarModificarCant.putExtra("idUsuario",idUsuario)
                startActivity(pasarModificarCant)
            }
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

                    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                        if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                            val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            requestPermissions(permission,storageCode)
                        }else{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                savePDF(data)
                            }
                        }
                    }else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            savePDF(data)
                        }
                    }
                    Toast.makeText(this,"¡Gracias por su compra ,la factura llegara a su correo electrónico!", Toast.LENGTH_LONG).show()

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

    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun savePDF(data: String){
        val mDoc = com.itextpdf.text.Document()
        val mFileDate = SimpleDateFormat("dd-MM-yyy HH:mm:ss", Locale.getDefault()).format(System.currentTimeMillis())
        val mFileName = "Factura_Cinepolis"

        var mFilePath : String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
            getActivity(this)?.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString() + "/" + mFileName + ".pdf"
        } else {
            Environment.getExternalStorageDirectory().toString() + "/" + mFileName + ".pdf"
        }
        println(mFilePath)
        try {
            PdfWriter.getInstance(mDoc,FileOutputStream(mFilePath))
            mDoc.open()

            mDoc.addAuthor("Cinépolis")
            mDoc.add(Chunk("Factura cinépolis $mFileDate"))
            mDoc.add(Paragraph("Productos:\n  $data \nCliente: "))
            mDoc.close()

            val arch = File(mFilePath)

            val intent = Intent(Intent.ACTION_VIEW)
            val fileURI = FileProvider.getUriForFile(
                this,
                this.applicationContext.packageName.toString() + ".provider", arch,
            )
            intent.setDataAndType(fileURI, "application/pdf")
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            try {
                this.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "No existe una aplicación para abrir el PDF",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }catch (e: Exception){
            Toast.makeText(this,""+e.toString(),Toast.LENGTH_SHORT).show()
            println(""+e.toString())
        }
    }

    @SuppressLint("MissingSuperCall")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            storageCode -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    val idUsuario = intent.extras!!.getInt("idUsuario")
                    val listaProductos : List<String> = obtenerProductos(idUsuario)
                    val data = obtenerInfoPdf(listaProductos)
                    savePDF(data)
                }else{
                    Toast.makeText(this, "Permiso denegado!", Toast.LENGTH_SHORT).show()
                }
            }
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
        objConexion?.close()
        return listaProductos
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

    fun obtenerPrecioComida(listaProductos: List<String>):Int{
        var precioTotal : Int = 0
        for (producto in listaProductos){
            var infoProducto: List<String> = producto.split("-")
            if ("Boleto" in infoProducto[1]){
                continue
            }else{
                precioTotal += obtenerPrecio(infoProducto[1],infoProducto[0].toInt())
            }
        }
        return precioTotal
    }

    fun obtenerPrecioBoletos(listaProductos: List<String>):Int{
        var montoBoletos = 0
        for (producto in listaProductos){
            if("Boleto" in producto){
                montoBoletos += producto.split("¢")[1].toInt()
            }else{
                continue
            }
        }
        return montoBoletos
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

    fun obtenerInfoPdf(listaProductos: List<String>):String{
        var infoPdf = ""
        var infoProducto: List<String>
        for (producto in listaProductos){
            println("Productos: $producto")
            if("Boleto" in producto){
                println("Es un boleto")
                infoPdf += producto + "\n"
            }else{
                infoProducto = producto.split("-")
                infoPdf += (producto + " ¢"+ obtenerPrecio(infoProducto[1],infoProducto[0].toInt()).toString() + "\n")
            }
        }
        return infoPdf
    }

}



