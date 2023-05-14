package com.example.mylistingmovie.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylistingmovie.R
import com.example.mylistingmovie.databinding.FragmentHomeBinding
import com.example.mylistingmovie.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var pref: SharedPreferences
    lateinit var movieadapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setVmAdapter()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)

        val fullname = pref.getString("username", "username")
        binding.welcome.text = "Welcome, $fullname!"




        //Profile
        binding.btnProfile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.btnFav.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }

    fun setVmAdapter() {
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getlivedatamovie().observe(this, Observer {
            movieadapter = MovieAdapter(it)
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvFilm.layoutManager = layoutManager
            binding.rvFilm.adapter = MovieAdapter(it)
        })

       viewModel.getAllMoviesRecom()




    }


}