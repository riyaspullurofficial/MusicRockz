package app.riyaspullur.musicrockz.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.riyaspullur.musicrockz.databinding.ActivityAboutUsBinding


class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.githubBtnIconID.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/riyaspullurofficial/MusicRockz"))
            startActivity(browserIntent)
        }
    }
}