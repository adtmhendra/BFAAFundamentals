package com.hendra.mynavigationdrawer.ui.subway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hendra.mynavigationdrawer.R
import kotlinx.android.synthetic.main.activity_subway.*

class SubwayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subway)
        supportActionBar?.title = "Subway"

        textSubway.text = resources.getString(R.string.this_is_subway_activity)
    }
}