package app.riyaspullur.musicrockz.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.riyaspullur.musicrockz.R
import app.riyaspullur.musicrockz.adapters.MusicTempAdapter
import app.riyaspullur.musicrockz.databinding.FragmentFolderListBinding
import app.riyaspullur.musicrockz.model.MusicTempDataClass

class FolderListFragment : Fragment() {

    private lateinit var binding: FragmentFolderListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFolderListBinding.inflate(layoutInflater)
        return binding.root

        /*inflater.inflate(R.layout.fragment_folder_list, container, false)*/

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



      /*  val musiclist = ArrayList<MusicTempDataClass>()
        for (i in 1..100) {
            musiclist.add(MusicTempDataClass("kada $i", "dasdsd $i"))

        }
        binding.recyclerListMusicID.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = MusicTempAdapter(musiclist, requireContext())

        }*/

      try {
          binding.recyclerListMusicID.apply {
              layoutManager = GridLayoutManager(requireContext(),2)
              adapter = MusicTempAdapter(AudioListFragment.musicListMA, requireContext())

          }
      }catch (e:Exception){
          Log.d("binding folderlist",e.toString())
      }





        binding.audioListBtnIDsFolderList.setOnClickListener {
            findNavController().navigate(R.id.action_folderListFragment_to_audioListFragment)
        }


        binding.suffleBtnIDFolderList.setOnClickListener {
            Toast.makeText(requireContext(), "ShuffleButton Click", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_folderListFragment_to_playingAudioFragment)

        }

        binding.favouriteBtnIDFolderList.setOnClickListener {
            Toast.makeText(requireContext(), "Favourite Button Click", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_folderListFragment_to_favouriteListFragment)
        }


        binding.playListBtnIDFolderList.setOnClickListener {
            Toast.makeText(requireContext(), "Play list Button Click", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_folderListFragment_to_playListFragment)


        }
    }

}