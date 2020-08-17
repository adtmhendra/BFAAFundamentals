package com.hendra.myviewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DecimalFormat

class MainViewModel : ViewModel() {

    val listWeather = MutableLiveData<ArrayList<WeatherItems>>()

    fun setWeather(city: String) {
        val listItems = ArrayList<WeatherItems>()

        val API_KEY = "dba60e2d973e05183aa0dc3a69cb99f3"
        val TAG = MainActivity::class.java.simpleName
        val url = "https://api.openweathermap.org/data/2.5/group?id=$city&units=metric&appid=$API_KEY"

        val client = AsyncHttpClient()
        Log.d(TAG, url)
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                try {
                    val body = String(responseBody)
                    val responseObject = JSONObject(body)
                    val list = responseObject.getJSONArray("list")

                    for (i in 0 until list.length()) {
                        val weather = list.getJSONObject(i)
                        val weatherItems = WeatherItems()
                        weatherItems.id = weather.getInt("id")
                        weatherItems.name = weather.getString("name")
                        weatherItems.currentWeather = weather.getJSONArray("weather").getJSONObject(0).getString("main")
                        weatherItems.description = weather.getJSONArray("weather").getJSONObject(0).getString("description")
                        val tempInKelvin = weather.getJSONObject("main").getDouble("temp")
                        val tempInCelcius = tempInKelvin - 273
                        val temperature = DecimalFormat("##.##").format(tempInCelcius)
                        weatherItems.temperature = temperature
                        listItems.add(weatherItems)
                    }
                    listWeather.postValue(listItems)

                } catch (e: Exception) {
                    Log.d(TAG, e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable) {
                Log.d(TAG, error.message.toString())
            }

        })
    }

    fun getWeather() : LiveData<ArrayList<WeatherItems>> {
        return listWeather
    }

}