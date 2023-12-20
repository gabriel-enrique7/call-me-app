package com.example.callme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.callme.adapters.CalendarAdapter
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {
    private lateinit var monthYearText : TextView
    private lateinit var calendarRecyclerView : RecyclerView
    private lateinit var selectedDate: LocalDate

    private lateinit var btnPrevius: Button
    private lateinit var btnNext: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        initWidgets()
        selectedDate = LocalDate.now()

        setMonthView()

        btnPrevius.setOnClickListener(View.OnClickListener {
            previusMonthAction()
        })

        btnNext.setOnClickListener(View.OnClickListener {
            nextMonthAction()
        })
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.tvMonthYear)
        btnPrevius = findViewById(R.id.btnPrevius)
        btnNext = findViewById(R.id.btnNext)
    }

    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
        var daysInMoth: ArrayList<String> = daysInMonthArray(selectedDate)

        val calendarAdapter: CalendarAdapter = CalendarAdapter(daysInMoth, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)

        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter

    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        var daysInMonthArray: ArrayList<String> = arrayListOf()
        var yearMonth: YearMonth = YearMonth.from(date)

        var daysInMonth: Int = yearMonth.lengthOfMonth()

        var firstOfMonth: LocalDate = selectedDate.withDayOfMonth(1)
        var dayOfWeek: Int = firstOfMonth.dayOfWeek.value

        for (i in 1 until 43) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }

        return daysInMonthArray
    }

    fun monthYearFromDate(date: LocalDate) : String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    override fun onItemClick(position: Int, dayText: String) {
        if (dayText.equals("")) {
            val message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun previusMonthAction() {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    fun nextMonthAction () {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }
}