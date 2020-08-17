package com.hendra.myservice

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class MyIntentService : IntentService("MyIntentService") {

    companion object {
        const val EXTRA_DURATION = "extra_duration"
        internal val TAG = MyIntentService::class.java.simpleName
    }

    override fun onHandleIntent(intent: Intent?) {

        Log.d(TAG, "onHandleIntent : Start")
        val duration = intent?.getLongExtra(EXTRA_DURATION, 0) as Long

        try {

            Thread.sleep(duration)
            Log.d(TAG, "onHandleIntent : Finish")

        } catch (e: InterruptedException) {
            e.printStackTrace()
            Thread.currentThread()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }
}
