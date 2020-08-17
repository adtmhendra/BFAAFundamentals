package com.hendra.mybroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var downloadReceiver: BroadcastReceiver

    companion object {
        const val ACTION_DOWNLOAD_STATUS = "action_download_status"
        private const val SMS_REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPermission.setOnClickListener(this)

        downloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d(DownloadService.TAG, "Hasil download diterima Main Activity...")
                Toast.makeText(this@MainActivity, "Download selesai", Toast.LENGTH_SHORT).show()
            }
        }
        val downloadIntentFilter = IntentFilter(ACTION_DOWNLOAD_STATUS)
        registerReceiver(downloadReceiver, downloadIntentFilter)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnPermission -> PermissionManager.check(this, android.Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE)
            R.id.btnDownload -> {
                val downloadIntent = Intent(this, DownloadService::class.java)
                startService(downloadIntent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(downloadReceiver)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when {
            grantResults[0] == PackageManager.PERMISSION_GRANTED -> Toast.makeText(this, "Permission received", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}