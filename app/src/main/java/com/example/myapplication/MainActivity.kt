package com.example.myapplication

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            datePicker()

        }
    }
    fun datePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day_of_month = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, day_of_month ->
                var textView= findViewById<TextView>(R.id.textView4)
                val selectedDates = "$year/$month/$day_of_month"
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDates)
                val dateInMinutes=theDate.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentTimeInMinutes=currentDate.time/60000
                val diff=currentTimeInMinutes-dateInMinutes
                var textViewr=findViewById<TextView>(R.id.textView5)
                textViewr.text=diff.toString()
                textView.text="$day_of_month/${month+1}/$year"
            },
            year,
            month,
            day_of_month
        ).show()
    }


}

