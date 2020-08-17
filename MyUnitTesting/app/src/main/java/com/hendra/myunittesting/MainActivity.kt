package com.hendra.myunittesting

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var tvResult: TextView
    private lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCalculateVolume: Button
    private lateinit var btnCalculateSurfaceArea: Button
    private lateinit var btnCalculateCircumference: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar?.title = "Cuboid"

        mainViewModel = MainViewModel(cuboidModel = CuboidModel())

        tvResult = findViewById(R.id.tv_result)
        edtLength = findViewById(R.id.edt_length)
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        btnSave = findViewById(R.id.btn_save)
        btnSave.setOnClickListener(this)
        btnCalculateVolume = findViewById(R.id.btn_calculate_volume)
        btnCalculateVolume.setOnClickListener(this)
        btnCalculateSurfaceArea = findViewById(R.id.btn_calculate_surface_area)
        btnCalculateSurfaceArea.setOnClickListener(this)
        btnCalculateCircumference = findViewById(R.id.btn_calculate_circumference)
        btnCalculateCircumference.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val length = edtLength.text.toString().trim()
        val width  = edtWidth.text.toString().trim()
        val height = edtHeight.text.toString().trim()

        when {
            length.isEmpty() -> edtLength.error = "Field ini tidak boleh kosong!"
            width.isEmpty()  -> edtWidth.error = "Field ini tidak boleh kosong!"
            height.isEmpty() -> edtHeight.error = "Field ini tidak boleh kosong!"
            else -> {
                val l = length.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()

                when (v.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(l, w, h)
                        visible()
                    }

                    R.id.btn_calculate_volume -> {
                        tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }

                    R.id.btn_calculate_surface_area -> {
                        tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }

                    R.id.btn_calculate_circumference -> {
                        tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        btnSave.visibility = View.GONE
        btnCalculateVolume.visibility = View.VISIBLE
        btnCalculateSurfaceArea.visibility = View.VISIBLE
        btnCalculateCircumference.visibility = View.VISIBLE
    }

    private fun gone() {
        btnSave.visibility = View.VISIBLE
        btnCalculateVolume.visibility = View.GONE
        btnCalculateSurfaceArea.visibility = View.GONE
        btnCalculateCircumference.visibility = View.GONE
    }
}