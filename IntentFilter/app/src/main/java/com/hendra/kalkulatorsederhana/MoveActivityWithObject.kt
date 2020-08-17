package com.hendra.kalkulatorsederhana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MoveActivityWithObject : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.title = "Pindah Activity dengan Object"
        }

        val tvReceivedObject : TextView = findViewById(R.id.tvReceivedObject)

        val person = intent.getParcelableArrayListExtra(EXTRA_PERSON) as ArrayList<Person>
        val text = """
            Name : ${person.name.toString()}
            Age : ${person.age} years old
            Address : ${person.address}
            City : ${person.city}
        """.trimIndent()
        tvReceivedObject.text = text
    }
}