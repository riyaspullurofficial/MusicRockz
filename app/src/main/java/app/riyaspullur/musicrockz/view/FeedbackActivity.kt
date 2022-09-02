package app.riyaspullur.musicrockz.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import app.riyaspullur.musicrockz.MainActivity
import app.riyaspullur.musicrockz.R
import app.riyaspullur.musicrockz.databinding.ActivityFeedbackBinding

class FeedbackActivity : AppCompatActivity() {
   private lateinit var binding:ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        binding.apply {

            btnCancelFavourite.setOnClickListener{
                startActivity(Intent(this@FeedbackActivity,MainActivity::class.java))
                finish()
            }
            btnSaveFavourite.setOnClickListener {
                Toast.makeText(this@FeedbackActivity,"Thanks For Your Feedback",Toast.LENGTH_LONG).show()
                startActivity(Intent(this@FeedbackActivity,MainActivity::class.java))
                finish()
            }



        }
    }
}