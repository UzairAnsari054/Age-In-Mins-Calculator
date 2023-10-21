package com.example.ageinminscalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate: Button = findViewById(R.id.btnSelectDate)

        btnSelectDate.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {

        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)

        val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)
        val tvDateInMins: TextView = findViewById(R.id.tvDateInMins)

        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)
                val selectedDateObj = sdf.parse(selectedDate)
                val selectedDateInMins = selectedDateObj.time / 60000

                val currentDateObj = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMins = currentDateObj.time / 60000


                val difference = currentDateInMins - selectedDateInMins
                tvDateInMins.text = difference.toString()

            },
            currentYear,
            currentMonth,
            currentDay
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 84600000
        dpd.show()
    }
}