package app.riyaspullur.musicrockz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app.riyaspullur.musicrockz.R
import app.riyaspullur.musicrockz.adapters.FavoriteListAdapterclass
import app.riyaspullur.musicrockz.adapters.MusicTempAdapter
import app.riyaspullur.musicrockz.databinding.FragmentFavouriteListBinding
import app.riyaspullur.musicrockz.model.MusicTempDataClass


class FavouriteListFragment : Fragment() {
    lateinit var favouriteFrBinding:FragmentFavouriteListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmen
        favouriteFrBinding=FragmentFavouriteListBinding.inflate(layoutInflater)
        return favouriteFrBinding.root

        //inflater.inflate(R.layout.fragment_favourite_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        favouriteFrBinding.backbuttonIDFavouriteFragment.setOnClickListener {
            findNavController().navigate(R.id.action_favouriteListFragment_to_audioListFragment)
        }


        /*val musiclist = ArrayList<MusicTempDataClass>()
        for (i in 1..100) {
            musiclist.add(MusicTempDataClass("kada $i", "dasdsd $i"))

        }
        favouriteFrBinding.favouriteSongsListRecyclerID.apply {
            layoutManager = GridLayoutManager(requireContext(),3)
            adapter = FavoriteListAdapterclass(musiclist, requireContext())

        }*/
    }
}