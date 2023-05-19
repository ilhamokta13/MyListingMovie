@file:Suppress("KotlinDeprecation")

package com.example.mylistingmovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.MovieDatabase
import com.example.mylistingmovie.databinding.FragmentFavoriteBinding
import com.example.mylistingmovie.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    private var mDBnew: MovieDatabase? = null
    private lateinit var adapter: FavoritMovieAdapter
    private lateinit var viewmodel : FavoriteViewModel


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
        viewmodel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        //ViewModel
        adapter = FavoritMovieAdapter(requireActivity(),ArrayList())

        binding.rvFavMovie.layoutManager = GridLayoutManager(context, 2)
        binding.rvFavMovie.adapter = adapter

        //LiveData
        viewmodel= ViewModelProvider(this)[FavoriteViewModel::class.java]
        viewmodel.getliveDataMoviefav().observe(viewLifecycleOwner, Observer {
            adapter.setMovie(it as ArrayList<FavoriteMovie>)
        })





    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            val listdata = mDBnew?.movieDao()!!.getFavoritMovie()
            activity?.runOnUiThread {
                adapter = FavoritMovieAdapter(requireActivity(), listdata)
                binding.rvFavMovie.layoutManager = GridLayoutManager(context, 2)
                binding.rvFavMovie.adapter = adapter


            }
        }
    }

}