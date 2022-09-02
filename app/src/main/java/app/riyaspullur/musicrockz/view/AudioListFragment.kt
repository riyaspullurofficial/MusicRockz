package app.riyaspullur.musicrockz.view


import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.core.app.ApplicationProvider
import app.riyaspullur.musicrockz.R
import app.riyaspullur.musicrockz.adapters.MusicTempAdapter
import app.riyaspullur.musicrockz.databinding.FragmentAudioListBinding
import app.riyaspullur.musicrockz.model.MusicData
import java.io.File


class AudioListFragment : Fragment() {
    val PERMISSION_CODE = 111

    private lateinit var binding: FragmentAudioListBinding


    companion object {
        var musicListMA: ArrayList<MusicData> = ArrayList()
        var navController: NavController? = null


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAudioListBinding.inflate(layoutInflater)
        return binding.root
        /*  inflater.inflate(R.layout.fragment_audio_list, container, false)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        if (!checkPermission()) {
            requestPermission()
        }
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        /*   val musiclist = ArrayList<MusicTempDataClass>()
           for (i in 1..100) {
               musiclist.add(MusicTempDataClass("kada $i", "dasdsd $i"))

           }
           binding.recyclerListMusicID.apply {
               layoutManager = LinearLayoutManager(requireContext())
               adapter = MusicTempAdapter(musiclist, requireContext())

           }*/

        //recucler set music

        try {
            musicListMA = getAllAudio()
            binding.recyclerListMusicID.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = MusicTempAdapter(musicListMA, requireContext())

            }
        } catch (e: Exception) {
            Log.d("AudioList", e.toString())
        }



        binding.suffleBtnID.setOnClickListener {

            if (PlayingAudioFragment.isPlaying) {
                findNavController().navigate(R.id.action_audioListFragment_to_playingAudioFragment)
            } else {
                Toast.makeText(requireContext(), "No Current Playing", Toast.LENGTH_SHORT).show()
            }

        }

        binding.favouriteBtnID.setOnClickListener {
          /*  Toast.makeText(requireContext(), "Favourite Button Click", Toast.LENGTH_SHORT).show()*/
            findNavController().navigate(R.id.action_audioListFragment_to_favouriteListFragment)
        }


        binding.playListBtnID.setOnClickListener {
      /*      Toast.makeText(requireContext(), "Play list Button Click", Toast.LENGTH_SHORT).show()*/
            findNavController().navigate(R.id.action_audioListFragment_to_playListFragment)


        }
        binding.audioListBtnIDsAudioList.setOnClickListener {
      /*      Toast.makeText(
                requireContext(),
                "Audio  list Button Click AudioList",
                Toast.LENGTH_SHORT
            ).show()*/
        }
        binding.folderListBtnIDsAudioList.setOnClickListener {
       /*     Toast.makeText(
                requireContext(),
                "Folder  list Button Click AudioList",
                Toast.LENGTH_SHORT
            ).show()*/
            findNavController().navigate(R.id.action_audioListFragment_to_folderListFragment)
        }


    }

    fun navigateMediaPlayer() {

        navController!!.navigate(R.id.action_audioListFragment_to_playingAudioFragment)
    }

    private fun requestRuntimePermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_CODE
            )
        }
    }

    /*override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireActivity(), "Permission GRanded", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Permission NOT GRanded", Toast.LENGTH_SHORT)
                    .show()

                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_CODE
                )

            }
        } else {
            Toast.makeText(requireActivity(), "Permission NOT GRanded", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_CODE
            )
        }
    }*/
    ////////////////////////////////////////////////////////////////////

    private fun checkPermission(): Boolean {
        return if (SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            val result =
                ContextCompat.checkSelfPermission(requireContext(), READ_EXTERNAL_STORAGE)
            val result1 =
                ContextCompat.checkSelfPermission(requireContext(), WRITE_EXTERNAL_STORAGE)
            result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = Uri.parse(
                    String.format(
                        "package:%s",
                        ApplicationProvider.getApplicationContext<Context>().packageName
                    )
                )
                startActivityForResult(intent, 2296)
            } catch (e: Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                startActivityForResult(intent, 2296)
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(WRITE_EXTERNAL_STORAGE),
                PERMISSION_CODE
            )
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2296) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Allow permission for storage access!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_CODE -> if (grantResults.size > 0) {
                val READ_EXTERNAL_STORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val WRITE_EXTERNAL_STORAGE = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (READ_EXTERNAL_STORAGE && WRITE_EXTERNAL_STORAGE) {
                    // perform action when allow permission success
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Allow permission for storage access!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }


    //load all Audio

    @SuppressLint("Range")
    private fun getAllAudio(): ArrayList<MusicData> {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // only for gingerbread and newer versions
        }
        val tempList = ArrayList<MusicData>()
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID,
        )
        val cursor = requireActivity().contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection, selection, null,
            MediaStore.Audio.Media.DATE_ADDED + " DESC"
        )

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    val titleC =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val idC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val albumC =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    val artistC =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val pathC = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val durationC =
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))

                    val albumIdC =
                        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
                            .toString()
                    val uri = Uri.parse("content://media/external/audio/albumart")
                    val artUriC = Uri.withAppendedPath(uri, albumIdC).toString()

                    val music = MusicData(
                        id = idC,
                        title = titleC,
                        album = albumC,
                        artist = artistC,
                        path = pathC,
                        duration = durationC,
                        artUri = artUriC

                    )


                    val file = File(music.path)
                    if (file.exists()) {
                        tempList.add(music)
                    }
                } while (cursor.moveToNext())
                cursor.close()
            }
        }




        return tempList
    }


}