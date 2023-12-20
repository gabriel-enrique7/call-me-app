package com.example.callme.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.callme.R

class CalendarViewHolder : RecyclerView.ViewHolder, View.OnClickListener {
    var dayOfMonth: TextView
    private var onItemListener: CalendarAdapter.OnItemListener

    constructor(itemView: View, onItemListener: CalendarAdapter.OnItemListener) : super(itemView) {
        this.dayOfMonth = itemView.findViewById(R.id.cellDayText)
        this.onItemListener = onItemListener

        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        onItemListener.onItemClick(adapterPosition, dayOfMonth.text.toString())
    }
}