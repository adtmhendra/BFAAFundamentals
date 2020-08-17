package com.hendra.myservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import java.sql.Time

class MyBoundService : Service() {

    companion object {
        private val TAG = MyBoundService::class.java.simpleName
    }

    private var myBinder = MyBinder()
    private val startTime = System.currentTimeMillis()

    private var mTimer: CountDownTimer = object : CountDownTimer(100000, 1000) {
        override fun onFinish() {
            TODO("Not yet implemented")
        }

        override fun onTick(millisUntilFinished: Long) {
            val elapsedTime = System.currentTimeMillis() - startTime
            Log.d(TAG, "onTick: $elapsedTime")
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: ")
        mTimer.start()
        return myBinder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "onUnbind: ")
        mTimer.cancel()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    internal inner class MyBinder : Binder() {
        val getService: MyBoundService = this@MyBoundService
    }

}
