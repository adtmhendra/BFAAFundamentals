package com.hendra.kalkulatorsederhana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MoveActivity : AppCompatActivity() {

    private lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move)

        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.title = "Bar Volume"
        }

        edtLength    = findViewById(R.id.edtLength)
        edtWidth     = findViewById(R.id.edtWidth)
        edtHeight    = findViewById(R.id.edtHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult     = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val inputLength = edtLength.text.toString().trim()
            val inputWidth  = edtHeight.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmptyField = false

            if (inputLength.isEmpty()) {
                isEmptyField = true
                edtLength.error = "Field ini tidak boleh kosong"
            }

            if (inputWidth.isEmpty()) {
                isEmptyField = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }

            if (inputHeight.isEmpty()) {
                isEmptyField = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }

            if (!isEmptyField) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
        }

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}