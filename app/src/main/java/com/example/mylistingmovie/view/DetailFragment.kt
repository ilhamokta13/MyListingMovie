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
import com.bumptech.glide.Glide
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.FavoriteMovieDao
import com.example.mylistingmovie.database.MovieDatabase
import com.example.mylistingmovie.databinding.FragmentDetailBinding
import com.example.mylistingmovie.model.Result
import com.example.mylistingmovie.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit var binding : FragmentDetailBinding
    private var moveDao : FavoriteMovieDao?=null
    private var moveDb : MovieDatabase?=null
    lateinit var viewModel : FavoriteViewModel
    private var id :Int?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = arguments?.getInt("id")

        moveDb= MovieDatabase.getInstance(requireContext())
        moveDao = moveDb?.movieDao()

        // pengambilan data
        val getData = arguments?.getSerializable("data_movie") as Result?
        val nama = getData!!.title
        val media = getData!!.mediaType
        val date = getData!!.releaseDate
        val sinopsis = getData!!.overview
        val image = getData!!.backdropPath

        binding.tvNamafilmdetail.text = nama
        binding.tvMediaTypeDate.text = media
        binding.tvDate.text = date
        binding.tvSinopsisfilmdetail.text = sinopsis

        Glide.with(view.context).load("https://image.tmdb.org/t/p/w780${image}")
            .into(binding.ivFilmimagedetail)


        binding.toggleFavorit.setOnClickListener {
            GlobalScope.async {
                val getfav = arguments?.getSerializable("data_movie") as Result
                val id = getData!!.id
                val judul = getData!!.title
                val ulasan = getData!!.overview
                val image = getData!!.backdropPath
                val hasil = moveDb?.movieDao()?.addToFavorit(FavoriteMovie(id, judul,ulasan, image))

                activity?.runOnUiThread {
                    if (hasil != 0.toLong()){
                        Toast.makeText(context, "Add to Favorit", Toast.LENGTH_LONG).show()
                        var _ischecked = false
                        _ischecked = !_ischecked
                        binding.toggleFavorit.isChecked = _ischecked
//                        Navigation.findNavController(it).navigate(R.id.action_detailFragment_to_favoriteFragment)
                    }else{
                        Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
                    }

                }






            }





        }

    }


}




