@file:Suppress("unused")

package com.example.mylistingmovie.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.FavoriteMovieDao
import com.example.mylistingmovie.database.MovieDatabase
import com.example.mylistingmovie.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var api : RestfulApi ) : ViewModel() {

    private var movefavDao : FavoriteMovieDao?=null
    private var movefavDb : MovieDatabase?=null
    @SuppressLint("StaticFieldLeak")
    lateinit var context : Context


     private lateinit var liveDataListfav: MutableLiveData<List<FavoriteMovie>>

    init {
        movefavDb = MovieDatabase.getInstance(context)
        movefavDao = movefavDb!!.movieDao()
    }


    fun getliveDataMoviefav(): MutableLiveData<List<FavoriteMovie>> {
        return  liveDataListfav
    }

//    fun getAllMoviesRecom() {
//        api.allMoviesRecom().enqueue(object : Callback<ListMovie> {
//            override fun onResponse(call: Call<ListMovie>, response: Response<ListMovie>) {
//                livedatamoviefav.value = response.body()?.results
//            }
//
//            override fun onFailure(call: Call<ListMovie>, t: Throwable) {
//                Log.d("Tag",t.message.toString())
//            }
//
//        })
//
//    }
}