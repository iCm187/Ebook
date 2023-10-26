package com.ibm.ebook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ibm.ebook.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val ebookKey = "ebookKey"
        const val imageKey = "imageKey"
        var items = mutableListOf<Item>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewItems()
    }

    private fun setViewItems() {
        val storedSearch = SharedPreferencesManager().getSearchCriteria(this)
        if (storedSearch != null) {
            binding.etBookSearch.setText(storedSearch)
        }

        binding.btnSearch.setOnClickListener {
            checkUserInput()
            callService()
            binding.btnSearch.visibility = View.INVISIBLE

        }

        binding.btnFavorites.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
    }

    private fun checkUserInput() {
        if (binding.etBookSearch.text.toString().isEmpty()) {
            Toast.makeText(this, "Veuillez effectuer une saisie", Toast.LENGTH_LONG).show()
        }
    }

    private fun callService() {
        val service: EbookApi.EbookService =
            EbookApi().getClient().create(EbookApi.EbookService::class.java)
        val call: Call<GoogleBooksApiResponse> =
            service.getEbooks(binding.etBookSearch.text.toString())
        call.enqueue(object : Callback<GoogleBooksApiResponse> {
            override fun onResponse(
                call: Call<GoogleBooksApiResponse>,
                response: Response<GoogleBooksApiResponse>
            ) {
                processResponse(response)
                searchEnded()
            }

            override fun onFailure(call: Call<GoogleBooksApiResponse>, t: Throwable) {
                processFailure(t)
                searchEnded()
            }
        })
    }

    private fun searchEnded() {
        binding.btnSearch.visibility = View.VISIBLE
        SharedPreferencesManager().saveSearchCriteria(binding.etBookSearch.text.toString(), this)
    }

    private fun processFailure(t: Throwable) {
        Toast.makeText(this, "Erreur", Toast.LENGTH_LONG).show()
        t.message?.let { Log.d("Erreur", it) }
    }

    private fun processResponse(response: Response<GoogleBooksApiResponse>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.items?.isNotEmpty() == true) {
                items = body.items
                startActivity(Intent(this, EbookListActivity::class.java))
            }
        } else {
            Toast.makeText(this, "Erreur lors de la récupération des données", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun gotoNextActivity(ebook: Item) {
        val intent = Intent(this, EbookDetailActivity::class.java)
        intent.putExtra(ebookKey, ebook.volumeInfo?.title)
        intent.putExtra(imageKey, ebook.volumeInfo?.imageLinks?.thumbnail)
        startActivity(intent)
    }
}
