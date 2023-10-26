package com.ibm.ebook

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EbookListViewAdapter(
    var data: List<Item>,
    var ebookItemCallback: EbookItemCallback
) : RecyclerView.Adapter<EbookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbookViewHolder {
        Log.d("viewHolder", "onBindViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.list_items, parent, false)
        return EbookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: EbookViewHolder, position: Int) {
        Log.d("viewHolder", "onBindViewHolder")


        holder.bind(data[position], ebookItemCallback)

    }
}
