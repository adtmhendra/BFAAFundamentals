package com.hendra.mybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvBooks: RecyclerView
    private var list: ArrayList<Book> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBooks = findViewById(R.id.rv_book)
        rvBooks.setHasFixedSize(true)

        list.addAll(BooksData.listBook)
        showRecyclerCardView()
    }

    private fun showRecyclerCardView() {
        rvBooks.layoutManager = LinearLayoutManager(this)
        val cardViewBookAdapter = CardViewBookAdapter(list)
        rvBooks.adapter = cardViewBookAdapter
    }
}