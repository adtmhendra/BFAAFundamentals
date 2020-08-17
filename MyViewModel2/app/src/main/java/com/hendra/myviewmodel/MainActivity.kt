package com.hendra.myviewmodel

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WeatherAdapter
    private lateinit var mainViewModel: MainViewModel
    val currentWeather = ArrayList<WeatherItems>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = WeatherAdapter(currentWeather)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        btnSearch.setOnClickListener {
            val city = edtCity.text.toString()
            if (city.isEmpty()) return@setOnClickListener
            showProgressBar(true)
            mainViewModel.setWeather(city)
        }

        mainViewModel.getWeather().observe(this, Observer { weatherItems ->
            if (weatherItems != null) {
                currentWeather.addAll(weatherItems)
                showProgressBar(false)
            }
        })
    }

    private fun showProgressBar(boolean: Boolean) {
        if (boolean) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

}