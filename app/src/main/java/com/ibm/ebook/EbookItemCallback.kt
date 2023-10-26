package com.ibm.ebook

interface EbookItemCallback {
    fun onCellClick(ebook: Item)
    fun onSaveEbook(ebook: Item)
    fun onEbookItemClicked(item: Item)
}

