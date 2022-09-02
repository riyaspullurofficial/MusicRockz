package app.riyaspullur.musicrockz.view

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.riyaspullur.musicrockz.R
import app.riyaspullur.musicrockz.adapters.MusicTempAdapter
import app.riyaspullur.musicrockz.constants.Constants
import app.riyaspullur.musicrockz.databinding.FragmentPlayingAudioBinding
import coil.load


class PlayingAudioFragment : Fragment() {

    companion object {
        var isPlaying = false
    }

    var mediaPlayer: MediaPlayer? = null

    var pos: Int? = null
    val musicList = MusicTempAdapter.musicList

    lateinit var binding: FragmentPlayingAudioBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayingAudioBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
        /*inflater.inflate(R.layout.fragment_playing_audio, container, false)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        isPlaying = true

        val pathSong = MusicTempAdapter.songPath
        if (MusicTempAdapter.positions != null) {
            pos = MusicTempAdapter.positions
            val newPath = musicList[pos!!].path
            val title = musicList[pos!!].title


            var nextOrPreviousPath = ""
            try {
                binding.imageIconID.load(musicList[pos!!].artUri)
                binding.endingTimerID.text =
                    Constants.formatDuration(MusicTempAdapter.musicList[pos!!].duration)
                /*   pathSong=requireArguments().getString("songPath")*/
                if (pathSong!!.isNotEmpty()) {
                    if (mediaPlayer == null) mediaPlayer = MediaPlayer()
                    mediaPlayer!!.reset()
                    mediaPlayer!!.setDataSource(newPath)
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.start()
                    binding.songNameIdFull.text = title
                    binding.playOrPause.setImageResource(R.drawable.ic_pause)
                }
                binding.seekBarMusicPlayingID.setOnSeekBarChangeListener(object :
                    OnSeekBarChangeListener {

                    override fun onStopTrackingTouch(seekBar: SeekBar) {
                        /*      Toast.makeText(requireContext(),"seek stop tracking",Toast.LENGTH_SHORT).show()*/
                        binding.seekBarMusicPlayingID.progress = seekBar.progress
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar) {
                        /*  Toast.makeText(requireContext(),"seek start tracking",Toast.LENGTH_SHORT).show()*/
                        binding.seekBarMusicPlayingID.progress = seekBar.progress


                    }

                    override fun onProgressChanged(
                        seekBar: SeekBar,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        if (mediaPlayer != null && fromUser) {
                            mediaPlayer!!.seekTo(progress * 1000)

                        }

                    }



                })


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
                    binding.playOrPause.setImageResource(R.drawable.ic_play)
                    isPlaying = false
                } else {
                    mediaPlayer!!.reset()
                    mediaPlayer!!.setDataSource(newPath)
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.start()
                    binding.playOrPause.setImageResource(R.drawable.ic_pause)
                    isPlaying = true
                }
            }
            binding.nextBtn.setOnClickListener {
                mediaPlayer!!.reset()
                mediaPlayer!!.stop()

                isPlaying = true
                pos = pos!! + 1
                nextOrPreviousPath = MusicTempAdapter.musicList[pos!!].path
                mediaPlayer!!.reset()
                mediaPlayer!!.setDataSource(nextOrPreviousPath)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
                val imagePath = MusicTempAdapter.musicList[pos!!].artUri
                imageLoad(imagePath)
                binding.songNameIdFull.text = MusicTempAdapter.musicList[pos!!].title
                binding.endingTimerID.text =
                    Constants.formatDuration(MusicTempAdapter.musicList[pos!!].duration)
                println("$pos next  : ${MusicTempAdapter.musicList[pos!!].path}")
            }
            binding.previousBtn.setOnClickListener {
                mediaPlayer!!.reset()
                mediaPlayer!!.stop()

                isPlaying = true
                pos = pos!! - 1
                nextOrPreviousPath = MusicTempAdapter.musicList[pos!!].path
                mediaPlayer!!.reset()
                mediaPlayer!!.setDataSource(nextOrPreviousPath)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
                binding.songNameIdFull.text = MusicTempAdapter.musicList[pos!!].title
                binding.endingTimerID.text =
                    Constants.formatDuration(MusicTempAdapter.musicList[pos!!].duration)
                val imagePath = MusicTempAdapter.musicList[pos!!].artUri

                imageLoad(imagePath)
                println("$pos prev  : ${MusicTempAdapter.musicList[pos!!].path}")

            }




            binding.backbuttonIDFavouriteFragment.setOnClickListener {
                findNavController().navigate(R.id.action_playingAudioFragment_to_audioListFragment)
                Toast.makeText(requireContext(), "fsflskflsfd", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun imageLoad(imagePath: String) {
        binding.imageIconID.load(imagePath)
    }

    override fun onStop() {
        if (isPlaying) {
            try {
                mediaPlayer!!.reset()

                mediaPlayer!!.prepare()
                mediaPlayer!!.stop()

            } catch (e: Exception) {
                Log.d("exception back ====", e.toString())
            }
        }
        super.onStop()
    }

}