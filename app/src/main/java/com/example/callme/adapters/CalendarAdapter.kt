package com.example.callme.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.callme.R

class CalendarAdapter : RecyclerView.Adapter<CalendarViewHolder> {
    private var daysOfMonth: ArrayList<String>
    private var onItemListener: OnItemListener

    constructor(daysOfMonth: ArrayList<String>, onItemListener: OnItemListener) : super() {
        this.daysOfMonth = daysOfMonth
        this.onItemListener = onItemListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams

        layoutParams.height = (parent.height * 0.166666666).toInt()

        return CalendarViewHolder(view, onItemListener)
    }

    override fun getItemCount(): Int {
        return  daysOfMonth.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.dayOfMonth.text = daysOfMonth.get(position)
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String)
    }
}