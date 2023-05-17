@file:Suppress("ReplaceGetOrSet")

package com.example.mylistingmovie.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylistingmovie.R
import com.example.mylistingmovie.databinding.FragmentHomeBinding
import com.example.mylistingmovie.viewmodel.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    private lateinit var movieadapter: MovieAdapter
//    private lateinit var uservm: UserViewModel
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var pref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setVmAdapter()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root


    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("Regist", Context.MODE_PRIVATE)
        val fullname = pref.getString("username", "username")
        binding.welcome.text = "Welcome, $fullname!"

//        uservm = ViewModelProvider(this).get(UserViewModel::class.java)
        firebaseAuth = FirebaseAuth.getInstance()

        //Profile
        binding.btnProfile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.btnFav.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_favoriteFragment)
        }

//        setUsername()
    }

//    private fun setUsername() {
//        val currentEmail = firebaseAuth.currentUser!!.email.toString()
//        uservm.getDataUser(currentEmail)
//
//        uservm.getDataUserProfile().observe(viewLifecycleOwner, Observer {
//            binding.username = it.username
//        })
//
//    }

    private fun setVmAdapter() {
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getlivedatamovie().observe(this) {
            movieadapter = MovieAdapter(it)
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvFilm.layoutManager = layoutManager
            binding.rvFilm.adapter = MovieAdapter(it)
        }

        viewModel.getAllMoviesRecom()


    }


}