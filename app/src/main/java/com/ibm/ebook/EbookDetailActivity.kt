package com.ibm.ebook

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class EbookDetailActivity(items: ArrayList<Item>, param: EbookItemCallback) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_ebook_detail)

        val ebookImage = intent.getStringExtra("ebookImageKey")
        val ebookTitle = intent.getStringExtra("ebookTitleKey")
        val ebookAuthor = intent.getStringExtra("ebookAuthorKey")
        val ebookDescription = intent.getStringExtra("ebookDescriptionKey")

        findViewById<TextView>(R.id.ebook_title_tv).text = ebookTitle
        findViewById<TextView>(R.id.ebook_author_tv).text = ebookAuthor
        findViewById<TextView>(R.id.ebook_description_tv).text = ebookDescription

        Glide.with(this).load(ebookImage).into(findViewById(R.id.ebook_iv))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
