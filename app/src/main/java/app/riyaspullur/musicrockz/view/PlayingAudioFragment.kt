package app.riyaspullur.musicrockz.view

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import app.riyaspullur.musicrockz.R
import app.riyaspullur.musicrockz.adapters.MusicTempAdapter
import app.riyaspullur.musicrockz.databinding.FragmentPlayingAudioBinding
import coil.load

class PlayingAudioFragment : Fragment() {
    var mediaPlayer: MediaPlayer? = null
    var isPlaying = true
    var pos: Int? = null
    val musicList=MusicTempAdapter.musicList

    lateinit var binding: FragmentPlayingAudioBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayingAudioBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
        /*inflater.inflate(R.layout.fragment_playing_audio, container, false)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val pathSong = MusicTempAdapter.songPath
        if (MusicTempAdapter.positions != null) {
            pos = MusicTempAdapter.positions
            var newPath = musicList[pos!!].path
            try {
                binding.imageIconID.load(musicList[pos!!].artUri)

                /*   pathSong=requireArguments().getString("songPath")*/
                if (pathSong!!.isNotEmpty()) {
                    if (mediaPlayer == null) mediaPlayer = MediaPlayer()
                    mediaPlayer!!.reset()
                    mediaPlayer!!.setDataSource(newPath)
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.start()
                }

                println("playing : $pathSong")
            } catch (e: Exception) {
                Log.d("player", e.toString())
            }
            binding.playOrPause.setOnClickListener {
                if (isPlaying) {
                    mediaPlayer!!.reset()
                    mediaPlayer!!.setDataSource(newPath)
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.pause()
                    isPlaying = false
                } else {
                    mediaPlayer!!.reset()
                    mediaPlayer!!.setDataSource(newPath)
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.start()
                    isPlaying = true
                }
            }
            binding.nextBtn.setOnClickListener {
                mediaPlayer!!.reset()
                mediaPlayer!!.setDataSource(newPath)
                mediaPlayer!!.prepare()
                mediaPlayer!!.stop()

                isPlaying = true
                pos = pos!! + 1
                MusicTempAdapter.musicList[pos!!].path
                mediaPlayer!!.reset()
                mediaPlayer!!.setDataSource(newPath)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()

                println("$pos next  : ${  MusicTempAdapter.musicList[pos!!].path}")
            }
            binding.previousBtn.setOnClickListener {
                mediaPlayer!!.reset()
                mediaPlayer!!.setDataSource(newPath)
                mediaPlayer!!.prepare()
                mediaPlayer!!.stop()

                isPlaying = true
                pos = pos!! - 1
                MusicTempAdapter.musicList[pos!!].path
                mediaPlayer!!.reset()
                mediaPlayer!!.setDataSource(newPath)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()

                println("$pos prev  : ${  MusicTempAdapter.musicList[pos!!].path}")

            }
            mediaPlayer!!.setOnCompletionListener {


            }




            binding.backbuttonIDPlaying.setOnClickListener {
                findNavController().navigate(R.id.action_playingAudioFragment_to_audioListFragment)
            }
        }
    }

}