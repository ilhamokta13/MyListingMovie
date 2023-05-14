package com.example.mylistingmovie.view

import android.graphics.Movie
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.mylistingmovie.R
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.FavoriteMovieDao
import com.example.mylistingmovie.database.MovieDatabase
import com.example.mylistingmovie.databinding.FragmentDetailBinding
import com.example.mylistingmovie.model.DetailMovie
import com.example.mylistingmovie.model.ListMovie
import com.example.mylistingmovie.model.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class DetailFragment : Fragment() {

    lateinit var binding : FragmentDetailBinding
    private var moveDao : FavoriteMovieDao?=null
    private var moveDb : MovieDatabase?=null
    private var id :Int?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moveDb= MovieDatabase.getInstance(requireContext())
        moveDao = moveDb?.MovieDao()

        // pengambilan data
        val getData = arguments?.getParcelable<DetailMovie>("data_movie") as DetailMovie
        val nama = getData.title
        val media = getData.media_type
        val date = getData.date
        val sinopsis = getData.overview
        val image = getData.image

        binding.tvNamafilmdetail.text = nama
        binding.tvMediaTypeDate.text = media
        binding.tvDate.text = date
        binding.tvSinopsisfilmdetail.text = sinopsis

        Glide.with(view.context).load("https://image.tmdb.org/t/p/w780${image}")
            .into(binding.ivFilmimagedetail)


        binding.toggleFavorit.setOnClickListener {

            GlobalScope.async {
                val bundle = Bundle()
                val fav = arguments?.putParcelable("data_fav", bundle) as Result
                val nama = fav.title
                val image = fav.backdropPath
                val ulasan = fav.overview

                val hasil = moveDb!!.MovieDao()?.addToFavorit(FavoriteMovie(id,nama,ulasan,image))



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




