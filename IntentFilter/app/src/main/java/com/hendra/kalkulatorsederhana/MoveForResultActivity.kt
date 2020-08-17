package com.hendra.kalkulatorsederhana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_move_for_result.*

class MoveForResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnChoose: Button
    private lateinit var rgNumber: RadioGroup

    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT = 110
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        btnChoose = findViewById(R.id.btnChoose)
        rgNumber = findViewById(R.id.rg_number)

        btnChoose.setOnClickListener(this)

        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.title = "Pindah Activity Membawa Data"
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnChoose) {
            if (rg_number.checkedRadioButtonId != 0) {
                var value = 0
                when (rg_number.checkedRadioButtonId) {
                    R.id.rb_50  -> value = 50
                    R.id.rb_100 -> value = 100
                    R.id.rb_150 -> value = 150
                    R.id.rb_200 -> value = 200
                    R.id.rb_250 -> value = 250
                }

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value)
                setResult(RESULT, resultIntent)
                finish()
            }
        }
    }
}