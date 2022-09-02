package app.riyaspullur.musicrockz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import app.riyaspullur.musicrockz.databinding.ActivityMainBinding
import app.riyaspullur.musicrockz.view.AboutUsActivity
import app.riyaspullur.musicrockz.view.FeedbackActivity
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //or nav drawer
        toggle = ActionBarDrawerToggle(this, binding.root, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navDrawerViewID.setNavigationItemSelectedListener(this)
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
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("ResourceType")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.feedBackNavID) {

            Toast.makeText(this, "Feedback", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, FeedbackActivity::class.java))



        } else if (item.itemId == R.id.aboutUsNavID) {
            Toast.makeText(this, "about Us", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, AboutUsActivity::class.java))
        } else if (item.itemId == R.id.exitNavID) {
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show()
            alertForExit()
        }

        return true
    }

    private fun alertForExit() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Do you want Exit?")
        builder.setMessage("Close your App")
        builder.setIcon(R.drawable.ic_exit)
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked yes", Toast.LENGTH_LONG).show()
            finishAffinity()
        }
//        //performing cancel action
//        builder.setNeutralButton("Cancel"){dialogInterface , which ->
//            Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
//        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(applicationContext, "clicked No", Toast.LENGTH_LONG).show()
        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }


}