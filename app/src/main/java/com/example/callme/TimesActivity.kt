package com.example.callme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.callme.adapters.TimesAdapter

class TimesActivity : AppCompatActivity(), TimesAdapter.OnItemListener {
    private lateinit var diaSemana: TextView
    private lateinit var diaMes: TextView
    private lateinit var timesRecyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_times)
        initWidgets()

        setTimesView()
    }

    private fun setTimesView() {
        var timesInDay: ArrayList<String> = timesInDayArray()

        val timesAdapter: TimesAdapter = TimesAdapter(timesInDay, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

        timesRecyclerView.layoutManager = layoutManager
        timesRecyclerView.adapter = timesAdapter
    }

    private fun timesInDayArray(): ArrayList<String> {
        var timesInDay: ArrayList<String> = arrayListOf()
        val initTime = 9
        val endTime = 18

        for (i in initTime until endTime+1) {
            timesInDay.add("${i}:00h")
        }

        return timesInDay
    }

    private fun initWidgets() {
        diaSemana = findViewById(R.id.tvNomeSemana)
        diaMes = findViewById(R.id.tvDiaMes)
        timesRecyclerView = findViewById(R.id.timesRecyclerView)
    }

    override fun onItemClick(position: Int, timeText: String) {
        val message = "Horario agendado para as " + timeText
        val toast: Toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)

        val toastView: View? = toast.view
        val toastMessage: TextView? = toastView?.findViewById(android.R.id.message)
        toastMessage?.setBackgroundResource(R.color.gray_100)

        toast.show()

        finish()
    }
}