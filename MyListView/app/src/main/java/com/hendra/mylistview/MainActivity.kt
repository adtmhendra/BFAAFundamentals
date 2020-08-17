package com.hendra.mylistview

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HeroAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataDesc: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var heroes = arrayListOf<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.supportActionBar?.title = "Data Pahlawan"

        val listViewHero: ListView = findViewById(R.id.lv_heroes)
        adapter = HeroAdapter(this)
        listViewHero.adapter = adapter

        listViewHero.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Anda memilih " + heroes[position].name, Toast.LENGTH_SHORT).show()
        }

        prepare()
        addItem()
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.data_name)
        dataDesc = resources.getStringArray(R.array.data_description)
        dataPhoto = resources.obtainTypedArray(R.array.data_photo)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val hero = Hero(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDesc[position]
            )
            heroes.add(hero)
        }
        adapter.heroes = heroes
    }
}