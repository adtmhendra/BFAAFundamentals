package com.hendra.mydeepnavigation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGoToDetail.setOnClickListener(this)

        showNotification(this@MainActivity, getString(R.string.notification_title), getString(R.string.notification_message), 110)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnGoToDetail -> {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_TITLE, getString(R.string.detail_title))
                intent.putExtra(DetailActivity.EXTRA_MESSAGE, getString(R.string.detail_message))
                startActivity(intent)
            }
        }
    }

    private fun showNotification(context: Context, title: String, message: String, notifId: Int) {

        val CHANNEL_ID = "channel_01"
        val CHANNEL_NAME = "Navigation channel"

        val notifDetailIntent = Intent(this, DetailActivity::class.java)
        notifDetailIntent.putExtra(DetailActivity.EXTRA_TITLE, title)
        notifDetailIntent.putExtra(DetailActivity.EXTRA_MESSAGE, message)

        val pendingIntent = TaskStackBuilder.create(this)
            .addParentStack(DetailActivity::class.java)
            .addNextIntent(notifDetailIntent)
            .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_baseline_email)
            .setColor(ContextCompat.getColor(context, android.R.color.black))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

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