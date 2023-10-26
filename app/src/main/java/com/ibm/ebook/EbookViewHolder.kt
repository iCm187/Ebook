package com.ibm.ebook

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

data class Ebook(val title: String, val imageURL: String)

class EbookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ebookImageIV: ImageView = itemView.findViewById(R.id.ebook_iv)
    private val ebookTitleTV: TextView = itemView.findViewById(R.id.ebook_title_tv)
    private val containerCL: ConstraintLayout = itemView.findViewById(R.id.container)
    private val bookmark: ImageView = itemView.findViewById(R.id.bookmark)

    fun bind(ebook: Item, ebookItemCallback: EbookItemCallback) {
        ebookTitleTV.text = ebook.volumeInfo?.title
        Log.d("viewHolder", ""+ ebook.volumeInfo?.title)
        Glide.with(ebookImageIV.context).load(ebook.volumeInfo?.imageLinks?.thumbnail)
            .into(ebookImageIV)
        containerCL.setOnClickListener {
            ebookItemCallback.onCellClick(ebook)
        }
        bookmark.setOnClickListener {
            ebookItemCallback.onSaveEbook(ebook)
        }
    }
}
