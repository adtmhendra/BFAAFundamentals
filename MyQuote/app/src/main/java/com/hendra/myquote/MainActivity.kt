package com.hendra.myquote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getRandomQuote()

        btnGoToListOfQuotes.setOnClickListener(this)

    }

    private fun getRandomQuote() {

        showProgressBar(true)
        val client = AsyncHttpClient()
        val url = "https://programming-quotes-api.herokuapp.com/quotes/random"
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                // if connection success

                showProgressBar(false)

                val result = String(responseBody)
                Log.d(TAG, result)

                try {

                    val jsonObject = JSONObject(result)
                    val quote = jsonObject.getString("en")
                    val author = jsonObject.getString("author")

                    tvQuote.text = quote
                    tvAuthor.text = author

                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                // if connection failed

                showProgressBar(false)

                val errorMessage = when(statusCode) {

                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"

                }

                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()

            }

        })

    }

    private fun showProgressBar(boolean: Boolean) {
        if (boolean == true) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.INVISIBLE
    }

    override fun onClick(v: View) {

        when(v.id) {

            R.id.btnGoToListOfQuotes -> startActivity(Intent(this, ListQuotesActivity::class.java))

        }

    }

}