package com.example.kotlinandroiddevelopment
import android.app.DatePickerDialog
import android.os.Build.VERSION_CODES.S
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.datepicker.DateSelector
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDate : TextView? = null
    private var inminutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateSelector = findViewById<Button>(R.id.DateSelector)
        selectedDate = findViewById(R.id.selectedDate)
        inminutes = findViewById(R.id.inminutes)
        dateSelector.setOnClickListener {
            clickDatePicker()
        }


        }


private fun clickDatePicker(){
    val myCalender = Calendar.getInstance()
    val year = myCalender.get(Calendar.YEAR)
    val month = myCalender.get(Calendar.MONTH)
    val day = myCalender.get(Calendar.DAY_OF_MONTH)
    val dpd = DatePickerDialog(this, {view, Selectedyear, Selectedmonth, SelecteddayOfMonth ->
        Toast.makeText(this , "You Are Awesome!!!!",Toast.LENGTH_SHORT).show()
        val selectedDate2 = "$SelecteddayOfMonth/${Selectedmonth+1}/$Selectedyear"
        selectedDate?.text = selectedDate2
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val theDate = sdf.parse(selectedDate2)// we are using the let so that the code will execute only if theDate val is not empty
        theDate?.let {
            val inMinutes = (theDate.time ?:0) / 60000    // This will us the time that has been spent in between the date in 1970 and the date that we have given
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) //This will give us the time that have been spent in between the 1970 and the current date
            currentDate?.let {
                val currentInMinutes = (currentDate.time ?: 0) /60000
                val answer = currentInMinutes - inMinutes
                inminutes?.text  = answer.toString()
            }


        }

    },
        year,
        month,
        day
    )
    dpd.show()
 // Date picker dialog needs to be shown that is why we used show() method here

}
}