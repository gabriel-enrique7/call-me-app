package com.example.callme.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.callme.R

class TimesAdapter : RecyclerView.Adapter<TimesViewHolder> {
    private var times: ArrayList<String>
    private var onItemListener: OnItemListener

    constructor(times: ArrayList<String>, onItemListener: OnItemListener) : super() {
        this.times = times
        this.onItemListener = onItemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimesViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.times_cell, parent, false)

        return TimesViewHolder(view, onItemListener)
    }

    override fun getItemCount(): Int {
        return times.size
    }

    override fun onBindViewHolder(holder: TimesViewHolder, position: Int) {
        holder.tvTimes.text = times.get(position)
    }

    interface OnItemListener {
        fun onItemClick(position: Int, timeText: String)
    }
}