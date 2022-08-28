package app.riyaspullur.musicrockz.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import app.riyaspullur.musicrockz.MainActivity
import app.riyaspullur.musicrockz.R


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        val handler = Handler()
        handler.postDelayed({
            val fp = Intent(applicationContext, MainActivity::class.java)
            startActivity(fp)
            finish()
        }, 5000)
    }
}