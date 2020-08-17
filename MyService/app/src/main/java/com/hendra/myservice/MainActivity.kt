package com.hendra.myservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var serviceBound = false
    private lateinit var myBoundService: MyBoundService

    private val serviceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            serviceBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myBinder = service as MyBoundService.MyBinder
            myBoundService = myBinder.getService
            serviceBound = true
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartService.setOnClickListener(this)
        btnStartIntentService.setOnClickListener(this)
        btnStartBoundService.setOnClickListener(this)
        btnStopBoundService.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when(v.id) {
            R.id.btnStartService -> {
                val startService = Intent(this@MainActivity, MyService::class.java)
                startService(startService)
            }

            R.id.btnStartIntentService -> {
                val startIntentService = Intent(this@MainActivity, MyIntentService::class.java)
                startIntentService.putExtra(MyIntentService.EXTRA_DURATION, 5000L)
                startService(startIntentService)
            }

            R.id.btnStartBoundService -> {
                val boundServiceIntent = Intent(this@MainActivity, MyBoundService::class.java)
                bindService(boundServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
            }

            R.id.btnStopBoundService -> unbindService(serviceConnection)

        }
    }

    override fun onDestroy() {

        super.onDestroy()
        if (serviceBound) {
            unbindService(serviceConnection)
        }

    }
}