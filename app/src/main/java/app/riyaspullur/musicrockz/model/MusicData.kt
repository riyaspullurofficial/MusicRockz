package app.riyaspullur.musicrockz.model

data class MusicData(
    val id:String,
    val title:String,
    val album:String,
    val artist:String,
    val duration:Long=0,
    val path:String,
    val artUri:String
)