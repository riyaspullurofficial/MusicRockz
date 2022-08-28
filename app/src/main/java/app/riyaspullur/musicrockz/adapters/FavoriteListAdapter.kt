package app.riyaspullur.musicrockz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.riyaspullur.musicrockz.R
import app.riyaspullur.musicrockz.model.MusicData
import app.riyaspullur.musicrockz.model.MusicTempDataClass

class FavoriteListAdapterclass(var list: List<MusicData>, var context: Context) :
    RecyclerView.Adapter<FavoriteListAdapterclass.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name=view.findViewById<TextView>(R.id.audioNameCardFavouriteID)
        val songDetails=view.findViewById<TextView>(R.id.songDetailsSectionCard)
        val duration=view.findViewById<TextView>(R.id.songDurationCardID)
        val image=view.findViewById<ImageView>(R.id.imageCardFavouriteID)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bindings =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favourite_card_layout, parent, false)
        return MyViewHolder(bindings)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.title
        holder.songDetails.text=item.artist
        holder.duration.text=item.duration.toString()


        holder.image.setOnClickListener {
            Toast.makeText(context, "Recycle", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int=list.size


/*    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val songName: TextView = view.findViewById(R.id.songNameIdCard)
        val songDescription: TextView = view.findViewById(R.id.songDetailsSectionCard)
        val image: ImageView = view.findViewById(R.id.musicIconIDCard)
    }*/

/*
    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: MusicTempAdapter.MyViewHolder, position: Int) {
        val item = list[position]
        holder.songName.text = item.name
        holder.songDescription.text = item.description

        holder.image.setOnClickListener {
            Toast.makeText(context, "Recycle", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteListAdapterclass.MyViewHolder {
        val bindings =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favourite_card_layout, parent, false)
        return MyViewHolder(bindings)
    }*/
}