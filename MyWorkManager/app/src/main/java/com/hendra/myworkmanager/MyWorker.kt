package com.hendra.myworkmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.SyncHttpClient
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DecimalFormat

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private var resultStatus: Result? = null

    companion object {
        private val TAG = MyWorker::class.java.simpleName
        const val EXTRA_CITY = "city"
        const val API_KEY = "dba60e2d973e05183aa0dc3a69cb99f3"

        const val NOTIF_ID = 1
        const val CHANNEL_ID = "channel_01"
        const val CHANNEL_NAME = "dicoding channel"
    }

    override fun doWork(): Result {
        val dataCity = inputData.getString(EXTRA_CITY)
        val result = getCurrentWeather(dataCity)
        return result
    }

    private fun getCurrentWeather(city: String?): Result {
        Log.d(TAG, "Mulai...")
        val client = SyncHttpClient()
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API_KEY"

        Log.d(TAG, "result = $url")
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                val result = String(responseBody)
                try {
                    val responseObject = JSONObject(result)
                    val currentWeather = responseObject.getJSONArray("weather").getJSONObject(0).getString("main")
                    val description = responseObject.getJSONArray("weather").getJSONObject(0).getString("description")
                    val temp = responseObject.getJSONObject("main").getDouble("temp")

                    val tempInCelcius = temp - 273
                    val temprature = DecimalFormat("##.##").format(tempInCelcius)

                    val title = "Current weather in $city"
                    val message = "$currentWeather, $description with $temprature celcius"

                    showNotification(title, message)

                    Log.d(TAG, "OnSuccess = Selesai...")
                    resultStatus = Result.success()

                } catch (e: Exception) {
                    Log.d(TAG, "OnSuccess: Gagal...")
                    resultStatus = Result.failure()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable) {
                Log.d(TAG, "OnFailure: Gagal...")
                // ketika proses gagal, maka jobFinished diset dengan parameter true. Yang artinya job perlu di reschedule
                showNotification("Get current weather failed", error.message)
                resultStatus = Result.failure()
            }

        })
        return resultStatus as Result
    }

    private fun showNotification(title: String, description: String?) {
        val notificationManager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_alarm)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notification.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIF_ID, notification.build())
    }
}