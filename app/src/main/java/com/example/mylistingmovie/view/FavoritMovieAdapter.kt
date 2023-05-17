package com.example.mylistingmovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.databinding.ItemMovieBinding

class FavoritMovieAdapter(private val movefav: List <FavoriteMovie>) : RecyclerView.Adapter<FavoritMovieAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNamafilm.text = movefav[position].title
        holder.binding.tvOverview.text = movefav[position].overview
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w780${movefav[position].image}")
            .into(holder.binding.ivFilmimage)


    }

    override fun getItemCount(): Int {
        return movefav.size
    }
}


