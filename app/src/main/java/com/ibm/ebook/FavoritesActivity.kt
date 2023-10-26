package com.ibm.ebook

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibm.ebook.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViewItems()
    }

    private fun setViewItems() {
        val localEbooks = SharedPreferencesManager().getLocalEbookStorage(this).localEbooks
        displayEbookList(localEbooks)
    }

    fun displayEbookList(ebooks: MutableList<Item>) {
        val adapter = EbookListViewAdapter(ebooks, object : EbookItemCallback {
            override fun onEbookItemClicked(item: Item) {
            }

            override fun onCellClick(ebook: Item) {
            }

            override fun onSaveEbook(ebook: Item) {
            }
        })
        binding.ebookRv.adapter = adapter
        binding.ebookRv.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
