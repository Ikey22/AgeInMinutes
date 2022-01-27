package app.isolvetech.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        date.setOnClickListener {
            pickDate(it)
        }
    }

    private fun pickDate(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvDate.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val ageInMinutes = theDate?.time?.div(60000)
            val ageInDays = ageInMinutes?.div(1440)
            Log.d("MY_TAG", ageInMinutes.toString())
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))


            val currentDateToMinutes = currentDate?.time?.div(60000)
            val currentDateToDays = currentDateToMinutes?.div(1440)

            val diffInMinutes = ageInMinutes?.let { currentDateToMinutes?.minus(it) }
            val diffInDays = ageInDays?.let { currentDateToDays?.minus(it) }

            //tvDateMinutes.text = diffInMinutes.toString()
            tvDateMinutes.text = diffInDays.toString()
        }
            , year, month, day)

        dpd.datePicker.maxDate = Date().time.minus(86400000)
        dpd.show()
    }
}