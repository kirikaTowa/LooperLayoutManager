package com.kakusummer.looperRecycleView.autoPollRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kakusummer.LooperRecycleView.R


class AutoPollAdapter(private val mData: List<String>) :
    RecyclerView.Adapter<AutoPollAdapter.BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_auto_poll, parent, false)
        val holder: BaseViewHolder = BaseViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val data = mData[position % mData.size]
        holder.tv.text = data
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById<TextView>(R.id.tv_content)
    }
}