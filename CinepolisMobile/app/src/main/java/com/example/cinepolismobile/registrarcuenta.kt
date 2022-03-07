package com.example.cinepolismobile

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class RegistrarCuenta : AppCompatActivity() {

    private lateinit var tvDatePicker : TextView
    private lateinit var ivDatePicker: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarcuenta)


        tvDatePicker = findViewById(R.id.verFecha)
        ivDatePicker = findViewById(R.id.calendario)

        val calendario = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { vista, año, mes, dia ->
            calendario.set(Calendar.YEAR,año)
            calendario.set(Calendar.MONTH,mes)
            calendario.set(Calendar.DAY_OF_MONTH,dia)
            updateLable(calendario)
        }

        ivDatePicker.setOnClickListener {
            DatePickerDialog(this, datePicker, calendario.get(Calendar.YEAR),calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)).show()
        }

    }

    private fun updateLable( calendario : Calendar){
        val formato = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(formato,Locale.US)
        tvDatePicker.setText(sdf.format(calendario.time))
    }



}