package com.hendra.mybackgroundthread

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val INPUT_STRING = "Halo ini Demo AsyncTask!"
        private const val LOG_ASYNC    = "DemoAsync"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStatus.text = resources.getString(R.string.status_pre)
        tvDesc.text   = INPUT_STRING

        GlobalScope.launch(Dispatchers.Default) {
            // background thread
            val input = INPUT_STRING
            var output: String? = null

            Log.d(LOG_ASYNC, "status : doInBackground")
            try {
                // output = menambahkan INPUT_STRING dengan String "Selamat belajar!"
                output = "$input Selamat belajar!"
                delay(3000)

                // pindah ke Main Thread untuk mengupdate UI
                withContext(Dispatchers.Main) {
                    tvStatus.text = resources.getString(R.string.status_post)
                    tvDesc.text   = output
                }
            } catch (e: Exception) {
                Log.d(LOG_ASYNC, e.message)
            }
        }
    }
}