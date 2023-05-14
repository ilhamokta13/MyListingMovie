package com.example.mylistingmovie.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylistingmovie.R
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.MovieDatabase
import com.example.mylistingmovie.databinding.FragmentFavoriteBinding
import com.example.mylistingmovie.model.DetailMovie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {
    lateinit var binding : FragmentFavoriteBinding
    private var mDBnew : MovieDatabase? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDBnew = MovieDatabase.getInstance(requireContext())
       getDataFav()



    }

    fun getDataFav(){

        binding.rvFavMovie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        GlobalScope.launch {
            val listdata = mDBnew?.MovieDao()!!.getFavoritMovie()
            activity?.runOnUiThread {
                listdata.let {
                    val adapt = FavoritMovieAdapter(it!!)
                    binding.rvFavMovie.adapter = adapt
                }
            }
        }

    }


}