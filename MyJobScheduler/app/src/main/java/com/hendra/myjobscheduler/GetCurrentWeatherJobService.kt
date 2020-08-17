package com.hendra.myjobscheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DecimalFormat

class GetCurrentWeatherJobService : JobService() {

    companion object {

        private val TAG = GetCurrentWeatherJobService::class.java.simpleName
        internal const val APP_ID = "dba60e2d973e05183aa0dc3a69cb99f3"
        internal const val CITY = "Jakarta"

    }

    override fun onStartJob(params: JobParameters): Boolean {

        Log.d(TAG, "onStartJob()")
        getCurrentWeather(params)

        return true

    }

    override fun onStopJob(params: JobParameters): Boolean {

        Log.d(TAG, "onStopJob()")
        return true

    }

    fun getCurrentWeather(jobParameters: JobParameters) {

        Log.d(TAG, "getCurrentWeather: mulai...")
        val client = AsyncHttpClient()
        val url = "http://api.openweathermap.org/data/2.5/weather?q=$CITY&appid=$APP_ID"
        Log.d(TAG, "getCurrentWeather: $url")
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {

                val result = String(responseBody)
                Log.d(TAG, result)
                try {

                    val jsonObject = JSONObject(result)

                    val currentWeather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")
                    val description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description")
                    val tempInKelvin = jsonObject.getJSONObject("main").getDouble("temp")

                    val tempToCelcius = tempInKelvin - 273
                    val temperatur = DecimalFormat("##.##").format(tempToCelcius)

                    val title = "Current Weather"
                    val message = "$currentWeather, $description with $temperatur celcius"
                    val notifId = 100

                    showNotification(applicationContext, title, message, notifId)

                    Log.d(TAG, "onSuccess: Selesai...")
                    jobFinished(jobParameters, false)

                } catch (e: Exception) {

                    Log.d(TAG, "Gagal...")
                    jobFinished(jobParameters, true)
                    e.printStackTrace()

                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {

                Log.d(TAG, "Gagal")
                jobFinished(jobParameters, true)

            }

        })

    }

    private fun showNotification(context: Context, title: String, message: String, notifId: Int) {

        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "Job Scheduler Channel"

        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_baseline_alarm)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.black))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL_ID)

            notificationManagerCompat.createNotificationChannel(channel)

        }

        val notification = builder.build()

        notificationManagerCompat.notify(notifId, notification)

    }

}