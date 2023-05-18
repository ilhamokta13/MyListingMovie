@file:Suppress("LocalVariableName", "DeferredResultUnused", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation"
)

package com.example.mylistingmovie.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.FavoriteMovieDao
import com.example.mylistingmovie.database.MovieDatabase
import com.example.mylistingmovie.databinding.FragmentDetailBinding
import com.example.mylistingmovie.model.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit var binding : FragmentDetailBinding
    private var moveDao : FavoriteMovieDao?=null
    private var moveDb : MovieDatabase?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        id = arguments?.getInt("id")

        moveDb= MovieDatabase.getInstance(requireContext())
        moveDao = moveDb?.movieDao()

        // pengambilan data
        val getData = arguments?.getSerializable("data_movie") as Result?
        val nama = getData!!.title
        val media = getData.mediaType
        val date = getData.releaseDate
        val sinopsis = getData.overview
        val image = getData.backdropPath

        binding.tvNamafilmdetail.text = nama
        binding.tvMediaTypeDate.text = media
        binding.tvDate.text = date
        binding.tvSinopsisfilmdetail.text = sinopsis

        Glide.with(view.context).load("https://image.tmdb.org/t/p/w780${image}")
            .into(binding.ivFilmimagedetail)


        binding.favoritesBtn.setOnClickListener {

                val getFav = arguments?.getSerializable("data_movie") as Result
                val judul = getFav.title
                val ulasan = getFav.overview
                val gambar = getFav.backdropPath

            GlobalScope.async {
                val favfilm =  FavoriteMovie(0,judul,ulasan,gambar)
                val result =moveDb?.movieDao()?.insertFilmFavorites(favfilm)
                activity?.runOnUiThread {
                    Toast.makeText(context, "Item added to Favorites", Toast.LENGTH_LONG)
                        .show()
                    Log.d("tes2", result.toString())
                    Log.d("tes3", judul)

                }
            }


        }

            }

        }








