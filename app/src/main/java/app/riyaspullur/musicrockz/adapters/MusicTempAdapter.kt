package app.riyaspullur.musicrockz.adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import app.riyaspullur.musicrockz.R
import app.riyaspullur.musicrockz.databinding.MusicDetailsCardBinding
import app.riyaspullur.musicrockz.model.MusicData
import app.riyaspullur.musicrockz.model.MusicTempDataClass
import app.riyaspullur.musicrockz.view.AudioListFragment
import coil.load
import java.util.concurrent.TimeUnit

class MusicTempAdapter(var list: ArrayList<MusicData>, var context: Context) :
    RecyclerView.Adapter<MusicTempAdapter.MyViewHolder>() {

    companion object{
        var songPath:String?=null
        var musicList=ArrayList<MusicData>()
        var positions:Int?=null
    }

    var audioListFr=AudioListFragment()
    class MyViewHolder(binding: MusicDetailsCardBinding) : RecyclerView.ViewHolder(binding.root) {

        val duration: TextView = binding.songDurationCardID
        val songName: TextView = binding.songNameIdCard
        val songDescription: TextView =binding.songDetailsSectionCard
        val image: ImageView = binding.musicIconIDCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      /*  val bindings =
            LayoutInflater.from(parent.context).inflate(R.layout.music_details_card, parent, false)
        return MyViewHolder(bindings)*/

        return MyViewHolder(MusicDetailsCardBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = list[position]
        holder.songName.text = item.title
        holder.songDescription.text = item.artist
        holder.duration.text =formatDuration(item.duration)
        holder.image.load(item.artUri)

        holder.itemView.setOnClickListener {
            musicList=list
            println("holder..........${position}")
            audioListFr.navigateMediaPlayer()
            println("DataaaaaaString path ${item.path}")
            songPath=item.path
            positions=position

        }

    }

    override fun getItemCount(): Int = list.size
}

fun formatDuration(duration: Long): String {
    val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
    val seconds = (TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS))
                            -minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES)

    return String.format("%2d:%02d",minutes,seconds)
}