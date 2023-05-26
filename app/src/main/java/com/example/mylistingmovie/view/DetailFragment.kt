@file:Suppress("LocalVariableName", "DeferredResultUnused", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation"
)

package com.example.mylistingmovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.FavoriteMovieDao
import com.example.mylistingmovie.database.MovieDatabase
import com.example.mylistingmovie.databinding.FragmentDetailBinding
import com.example.mylistingmovie.model.DetailMovie
import com.example.mylistingmovie.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DEPRECATION", "NAME_SHADOWING")
@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private var moveDao: FavoriteMovieDao? = null
    private var moveDb: MovieDatabase? = null
    private lateinit var viewmodel: FavoriteViewModel
    private var id :Int?=null



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

        viewmodel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        moveDb = MovieDatabase.getInstance(requireContext())
        moveDao = moveDb?.movieDao()

        // pengambilan data
        id = arguments?.getInt("id")
        val getData = arguments?.getParcelable<DetailMovie>("data_movie") as DetailMovie
        val nama = getData.title
        val date = getData.date
        val sinopsis = getData.overview
        val language = getData.language
        val image = getData.image
        val popularity = getData.popularity



        binding.tvNamafilmdetail.text = nama
        binding.tvDate.text = date
        binding.tvSinopsisfilmdetail.text = sinopsis
        binding.tvLanguage.text = language
        binding.tvPopularity.text = popularity.toString()

        Glide.with(view.context).load("https://image.tmdb.org/t/p/w780${image}")
            .into(binding.ivFilmimagedetail)


        binding.toggleFavorit.setOnClickListener {
            GlobalScope.async {
                val getFav = arguments?.getParcelable<DetailMovie>("data_movie") as DetailMovie
                val idd = getFav.id
                val judul = getFav.title
                val release = getFav.date
                val gambar = getFav.image
                val overview = getFav.overview
                val language = getFav.language
                val popular = getFav.popularity.toString()
                val hasil = moveDb?.movieDao()?.addToFavorit(FavoriteMovie(idd, judul, release, gambar,overview,language,popular))

                activity?.runOnUiThread {
                    if (hasil != 0.toLong()){
                        Toast.makeText(context, "Add to Favorit", Toast.LENGTH_LONG).show()
                        var _isChecked = false
                        _isChecked = !_isChecked
                        binding.toggleFavorit.isChecked = _isChecked

                    }else{
                        Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
                    }
                }


            }


            }


        }

    }











