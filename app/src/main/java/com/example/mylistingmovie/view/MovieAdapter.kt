package com.example.mylistingmovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylistingmovie.R
import com.example.mylistingmovie.databinding.ItemMovieBinding
import com.example.mylistingmovie.model.DetailMovie
import com.example.mylistingmovie.model.Result

class MovieAdapter ( var listmovie : List<Result>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder (var binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvNamafilm.text = listmovie[position].title
        holder.binding.tvOverview.text = listmovie[position].overview
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w780${listmovie[position].backdropPath}")
            .into(holder.binding.ivFilmimage)

        holder.binding.cardFilm.setOnClickListener {
            var image = listmovie[position].backdropPath.toString()
            var title = listmovie[position].title.toString()
            var mediatype = listmovie[position].mediaType.toString()
            var date = listmovie[position].releaseDate.toString()
            var overview = listmovie[position].overview.toString()
            val id = listmovie[position].id.toString().toInt()

            var detail = DetailMovie(image,title,mediatype, date, overview, id.toString())

            val data = Bundle()
            data.putParcelable("data_movie",detail)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment,data)

        }

    }

        override fun getItemCount(): Int {
            return listmovie.size
        }
    }
