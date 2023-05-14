package com.example.mylistingmovie.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.FavoriteMovieDao
import com.example.mylistingmovie.database.MovieDatabase

class FavoriteViewModel(context : Context) : ViewModel() {

    private var movefavDao : FavoriteMovieDao?=null
    private var movefavDb : MovieDatabase?=null


    lateinit var liveDataListfav: MutableLiveData<List<FavoriteMovie>>

    init {
        movefavDb = MovieDatabase.getInstance(context)
        movefavDao = movefavDb!!.MovieDao()
    }


    fun getliveDataMoviefav(): MutableLiveData<List<FavoriteMovie>> {
        return  liveDataListfav
    }
}