@file:Suppress("DeferredResultUnused")

package com.example.mylistingmovie.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mylistingmovie.R
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.MovieDatabase
import com.example.mylistingmovie.databinding.ItemFavmovieBinding
import com.example.mylistingmovie.model.DetailMovie
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("unused", "CAST_NEVER_SUCCEEDS")
class FavoritMovieAdapter(private var context : Context,  private var movefav: List <FavoriteMovie>) : RecyclerView.Adapter<FavoritMovieAdapter.ViewHolder>() {
    private var filmFavDB: MovieDatabase? = null
    class ViewHolder(var binding : ItemFavmovieBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFavmovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)

    }


    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNamafilm.text = movefav[position].title
        holder.binding.tvRelease.text = movefav[position].release
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w780${movefav[position].image}")
            .into(holder.binding.ivFilmimage)


        holder.binding.detailFav.setOnClickListener {
            val image = movefav[position].image
            val title = movefav[position].title
            val date = movefav[position].release
            val overview = movefav[position].overview
            val language = movefav[position].language
            val popularity = movefav[position].popularity.toDouble()
            val id = movefav[position].id!!.toInt()
            val detail = DetailMovie(image,title, date, overview, language,popularity, id)
            val bundle = Bundle()
            bundle.putParcelable("data_movie", detail)
            Navigation.findNavController(it).navigate(R.id.action_favoriteFragment_to_detailFragment,bundle)
        }


        holder.binding.favoritesCheckBox.setOnClickListener {
            var isFavorites = holder.binding.favoritesCheckBox.isChecked
            if (isFavorites){
                filmFavDB = MovieDatabase.getInstance(it.context)

                AlertDialog.Builder(it.context)
                    .setTitle("Hapus Data")
                    .setMessage("Yakin Hapus Data")
                    .setPositiveButton("Ya") { _: DialogInterface, _: Int ->
                        GlobalScope.async {
                            val result = filmFavDB?.movieDao()?.deleteFilmFavorites(
                                movefav[position])

                            (holder.itemView.context as MainActivity).runOnUiThread {
                                if (result != 0) {
                                    Toast.makeText(it.context, "Data ${movefav[position].title} Terhapus", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(it.context, "Data ${movefav[position].title} Gagal terhapus", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                    .setNegativeButton("Tidak") { dialogInterface: DialogInterface, _: Int ->
                        dialogInterface.dismiss()
                        isFavorites = holder.binding.favoritesCheckBox.isChecked
                    }
                    .show()


            }
        }




    }

    override fun getItemCount(): Int {
        return movefav.size
    }

    fun setMovie(itemMovie: ArrayList<FavoriteMovie>) {
        this.movefav = itemMovie
    }


}


