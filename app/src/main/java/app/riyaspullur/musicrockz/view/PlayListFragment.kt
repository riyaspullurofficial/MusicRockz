package app.riyaspullur.musicrockz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import app.riyaspullur.musicrockz.R
import app.riyaspullur.musicrockz.databinding.FragmentFavouriteListBinding
import app.riyaspullur.musicrockz.databinding.FragmentPlayListBinding

class PlayListFragment : Fragment() {
    lateinit var binding:FragmentPlayListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentPlayListBinding.inflate(layoutInflater)
        return binding.root

    /*inflater.inflate(R.layout.fragment_play_list, container, false)*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding.backbuttonIDPlayList.setOnClickListener {
            findNavController().navigate(R.id.action_playListFragment_to_audioListFragment)
        }




    }
}