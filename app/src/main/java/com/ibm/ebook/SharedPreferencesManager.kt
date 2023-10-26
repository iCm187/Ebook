package com.ibm.ebook

import android.content.Context
import android.util.Log
import com.google.gson.Gson

class SharedPreferencesManager {
    companion object {
        const val ebookListKey = "ebookListKey"
        const val searchKey = "searchKey"
        const val preferencesFile = "preferencesFile"
    }

    fun saveSearchCriteria(search: String, context: Context) {
        val sharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString(searchKey, search)
            .apply()
    }

    fun getSearchCriteria(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        return sharedPreferences.getString(searchKey, "")
    }

    fun getLocalEbookStorage(context: Context): LocalEbookStorage {
        val sharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        val gson = Gson()
        val json: String? = sharedPreferences.getString(ebookListKey, "")
        if (json.isNullOrEmpty()) {
            return LocalEbookStorage(mutableListOf())
        }
        return gson.fromJson(json, LocalEbookStorage::class.java)
    }

    fun saveEbook(ebook: Item, context: Context) {
        var localEbookStorage = getLocalEbookStorage(context)
        localEbookStorage.localEbooks.add(ebook)
        Log.d("saveEbook", " size " + localEbookStorage.localEbooks.size)
        val sharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(localEbookStorage)
        sharedPreferences.edit().putString(ebookListKey, json).apply()
    }
}
