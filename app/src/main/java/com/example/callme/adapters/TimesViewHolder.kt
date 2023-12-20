package com.example.callme.adapters

import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.callme.R

class TimesViewHolder : RecyclerView.ViewHolder, OnClickListener {
    var tvTimes: TextView
    private var onItemListener: TimesAdapter.OnItemListener

    constructor(itemView: View, onItemListener: TimesAdapter.OnItemListener) : super(itemView) {
        this.tvTimes = itemView.findViewById(R.id.tvTime)
        this.onItemListener = onItemListener

        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        onItemListener.onItemClick(adapterPosition, tvTimes.text.toString())
    }
}