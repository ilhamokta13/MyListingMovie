package com.example.mylistingmovie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylistingmovie.R
import com.example.mylistingmovie.databinding.ItemMovieBinding
import com.example.mylistingmovie.model.Result

class MovieAdapter (private var listmovie : List<Result>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder (var binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

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

            val data = Bundle()
            data.putSerializable("data_movie",listmovie[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment,data)

        }

    }

        override fun getItemCount(): Int {
            return listmovie.size
        }
    }
