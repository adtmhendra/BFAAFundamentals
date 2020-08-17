package com.hendra.mybook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewBookAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<CardViewBookAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_book, parent, false)
        return CardViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val book = listBook[position]

        Glide.with(holder.itemView.context)
            .load(book.bookImage)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgBook)

        holder.tvBookName.text = book.bookName
        holder.tvBookPublisher.text = book.bookPublisher

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, listBook[holder.adapterPosition].bookName, Toast.LENGTH_SHORT).show()
        }
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgBook: ImageView = itemView.findViewById(R.id.img_book_images)
        var tvBookName: TextView = itemView.findViewById(R.id.tv_book_name)
        var tvBookPublisher: TextView = itemView.findViewById(R.id.tv_book_publisher)
    }
}