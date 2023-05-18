@file:Suppress("KotlinDeprecation")

package com.example.mylistingmovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylistingmovie.database.MovieDatabase
import com.example.mylistingmovie.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    private var mDBnew: MovieDatabase? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDBnew = MovieDatabase.getInstance(requireContext())
        getDataFav()


    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getDataFav() {

        binding.rvFavMovie.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch {
            val listdata = mDBnew?.movieDao()!!.getAllFilmFavorites()
            activity?.runOnUiThread {
                listdata.observe(viewLifecycleOwner) {
                    //set adapter
                    binding.rvFavMovie.adapter = FavoritMovieAdapter(it)
                }
            }
        }

    }


//    fun setVmAdapter() {
//        val viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
//        viewModel.getlivedatamovie().observe(this, Observer {
//            movieadapterfav = FavoritMovieAdapter(it)
//            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            binding.rvFavMovie.layoutManager = layoutManager
//            binding.rvFavMovie.adapter = MovieAdapter(it)
//        })
//
//        viewModel.getAllMoviesRecom()
//
//
//    }

}