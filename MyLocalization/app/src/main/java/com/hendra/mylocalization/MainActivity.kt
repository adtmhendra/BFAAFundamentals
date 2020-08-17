package com.hendra.mylocalization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokeCount = 3
        val hello = resources.getString(R.string.hello_world, "Hendra Pratik Aditama", pokeCount, "pacar kamu")
        tvHelloWorld.text = hello

        val songCount = 5
        val tv_plural = resources.getQuantityString(R.plurals.numberOfSongsAvailable, songCount, songCount)
        tvPlural.text = tv_plural

        tvXliff.text = resources.getString(R.string.app_homeurl)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_language) {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}