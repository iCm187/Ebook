package com.ibm.ebook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EbookListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.ebookRv)

        val adapter = EbookListViewAdapter(MainActivity.items, object : EbookItemCallback {
            override fun onCellClick(ebook: Item) {
            }

            override fun onSaveEbook(ebook: Item) {
            }

            override fun onEbookItemClicked(item: Item) {
                val intent = Intent(this@EbookListActivity, EbookDetailActivity::class.java).apply {
                    putExtra("ebookImageKey", item.volumeInfo?.imageLinks?.thumbnail)
                    putExtra("ebookTitleKey", item.volumeInfo?.title)
                    putExtra("ebookAuthorKey", item.volumeInfo?.authors?.joinToString(", "))
                    putExtra("ebookDescriptionKey", item.volumeInfo?.description)
                }
                startActivity(intent)
            }

        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        Log.d("viewHolder", "onBindViewHolder"+ MainActivity.items.size)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
