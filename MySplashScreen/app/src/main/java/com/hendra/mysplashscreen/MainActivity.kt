package com.hendra.mysplashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.os.postDelayed
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_SCREEN : Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)

        splashScreenImage.animation = topAnim
        splashScreenText.animation = bottomAnim

        Handler().postDelayed({
            val moveIntent = Intent(this@MainActivity, NavigationDrawer::class.java)
            startActivity(moveIntent)
            finish()
        }, SPLASH_SCREEN)
    }
}