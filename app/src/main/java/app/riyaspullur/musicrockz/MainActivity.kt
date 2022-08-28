package app.riyaspullur.musicrockz

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import app.riyaspullur.musicrockz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //or nav drawer
        toggle= ActionBarDrawerToggle(this,binding.root,R.string.open,R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


//not working
        /*binding.navDrawerViewID.setOnClickListener{
            when(it.id){
                R.id.exitNavID->{
                    println("exit")
                }
            }
        }*/

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}