package com.hendra.myviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_items.view.*

class WeatherAdapter(val listWeatherItems : ArrayList<WeatherItems>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WeatherViewHolder = WeatherViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.weather_items, viewGroup, false))

    override fun getItemCount(): Int = listWeatherItems.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherItems = listWeatherItems[position]

        holder.bindItem(weatherItems)
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(weatherItems: WeatherItems) {
            with(itemView) {
                txtCity.text = weatherItems.name
                txtTemp.text = weatherItems.temperature
                txtDesc.text = weatherItems.description
            }
        }
    }

}