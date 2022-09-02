package app.riyaspullur.musicrockz.constants

import android.net.Uri
import java.util.concurrent.TimeUnit

class Constants {
    companion object {

        fun formatDuration(duration: Long): String {
            val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
            val seconds = (TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS))
            -minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES)

            return String.format("%2d:%02d", minutes, seconds)
        }
    }
}