package com.hendra.myquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_list_quotes.*
import org.json.JSONArray
import java.lang.Exception

class ListQuotesActivity : AppCompatActivity() {

    companion object {
        private val TAG = ListQuotesActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_quotes)

        getListOfQuotes()

    }

    private fun getListOfQuotes() {

        showProgressBar(true)

        val client = AsyncHttpClient()
        val url = "https://programming-quotes-api.herokuapp.com/quotes/page/1"
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                // if connection success
                showProgressBar(false)

                val listOfQuotes = ArrayList<String>()

                val result = String(responseBody)

                Log.d(TAG, result)
                try {

                    val jsonArray = JSONArray(result)

                    for (i in 0 until jsonArray.length()) {

                        val jsonObject = jsonArray.getJSONObject(i)
                        val quote = jsonObject.getString("en")
                        val author = jsonObject.getString("author")

                        listOfQuotes.add("\n$quote\n — $author\n")

                    }

                    val adapter = ArrayAdapter(this@ListQuotesActivity, android.R.layout.simple_list_item_1, listOfQuotes)
                    listQuotes.adapter = adapter

                } catch (e: Exception) {

                    Toast.makeText(this@ListQuotesActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()

                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {

                Toast.makeText(this@ListQuotesActivity, error.message, Toast.LENGTH_SHORT).show()

            }


        })

    }

    private fun showProgressBar(boolean: Boolean) {
        if (boolean == true) progressBarList.visibility = View.VISIBLE
        else progressBarList.visibility = View.INVISIBLE
    }

}