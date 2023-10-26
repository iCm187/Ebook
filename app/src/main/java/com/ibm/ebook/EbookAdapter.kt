package com.ibm.ebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EbookAdapter(private val items: ArrayList<Item>, private val callback: EbookItemCallback) : RecyclerView.Adapter<EbookAdapter.EbookViewHolder>() {

    class EbookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titre: TextView = view.findViewById(R.id.ebook_title_tv)
        val auteur: TextView = view.findViewById(R.id.ebook_author_tv)
        val description: TextView = view.findViewById(R.id.ebook_description_tv)
        val image: ImageView = view.findViewById(R.id.ebook_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return EbookViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EbookViewHolder, position: Int) {
        val item = items[position]
        holder.titre.text = item.volumeInfo?.title
        holder.auteur.text = item.volumeInfo?.authors?.joinToString(", ")
        holder.description.text = item.volumeInfo?.description

        Glide.with(holder.itemView.context).load(item.volumeInfo?.imageLinks?.thumbnail).into(holder.image)

        holder.itemView.setOnClickListener {
            callback.onEbookItemClicked(item)
        }
    }
}
