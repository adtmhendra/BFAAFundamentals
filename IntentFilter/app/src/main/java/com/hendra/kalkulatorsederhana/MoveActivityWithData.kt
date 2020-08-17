package com.hendra.kalkulatorsederhana

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MoveActivityWithData : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_AGE  = "extra_age"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_data)

        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.title = "Pindah Activity dengan Data"
        }

        val tvDataReceived: TextView = findViewById(R.id.tv_data_received)

        val name = intent.getStringExtra(EXTRA_NAME)
        val age  = intent.getIntExtra(EXTRA_AGE, 0)

        val text = """
            My name is $name,
            and i am $age years old
        """.trimIndent()
        tvDataReceived.text = text
    }
}