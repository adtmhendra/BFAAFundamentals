package com.hendra.mylistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class HeroAdapter internal constructor(private val context: Context): BaseAdapter() {

    internal var heroes = arrayListOf<Hero>()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var itemView = view
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false)
        }

        val viewHolder = ViewHolder(itemView as View)
        val hero = getItem(position) as Hero
        viewHolder.bind(hero)

        return itemView
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val tvName: TextView = view.findViewById(R.id.tv_name)
        private val tvDesc: TextView = view.findViewById(R.id.tv_description)
        private val imgPhoto: ImageView = view.findViewById(R.id.img_photo)

        internal fun bind(hero: Hero) {
            tvName.text = hero.name
            tvDesc.text = hero.description
            imgPhoto.setImageResource(hero.photo)
        }
    }

    override fun getItem(i: Int): Any = heroes[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getCount(): Int = heroes.size
}