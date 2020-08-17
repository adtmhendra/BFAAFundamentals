package com.hendra.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Custom View"

        setMyButtonEnable()
        myEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

        })

        myButton.setOnClickListener { Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show() }
    }

    private fun setMyButtonEnable() {
        val result = myEditText.text
        myButton.isEnabled = result != null && result.toString().isEmpty()
    }
}