package com.hendra.mybroadcastreceiver

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import java.lang.Exception

class DownloadService : IntentService("DownloadService") {

    companion object {
        val TAG = DownloadService::class.java.simpleName
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "Download service dijalankan Download Service...")

        if (intent != null) {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
            sendBroadcast(notifyFinishIntent)
        }
    }
}
