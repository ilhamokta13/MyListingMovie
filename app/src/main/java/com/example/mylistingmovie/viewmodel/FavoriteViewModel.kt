@file:Suppress("unused")

package com.example.mylistingmovie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.FavoriteMovieDao
import com.example.mylistingmovie.database.MovieDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(app:Application) : AndroidViewModel(app){

    private var movefavDao : FavoriteMovieDao?=null
    private var movefavDb : MovieDatabase?=null

    private var liveDataListfav: MutableLiveData<List<FavoriteMovie>> = MutableLiveData()

    init {
        getAllMoviePopular()
    }


    fun getliveDataMoviefav(): MutableLiveData<List<FavoriteMovie>> {
        return  liveDataListfav
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllMoviePopular() {

        GlobalScope.launch {
            val dataDao = MovieDatabase.getInstance(getApplication())!!.movieDao()
            val listNote = dataDao.getFavoritMovie()
            liveDataListfav.postValue(listNote)

        }
    }



    suspend fun delete(favoritMovie : FavoriteMovie) {
        val dataDao = MovieDatabase.getInstance(getApplication())!!.movieDao()
        dataDao.deleteFilmFavorites(favoritMovie)
        getAllMoviePopular()
    }

    suspend fun insert(favoritMovie : FavoriteMovie){
        val dataDao = MovieDatabase.getInstance(getApplication())!!.movieDao()
        dataDao.addToFavorit(favoritMovie)
        getAllMoviePopular()
    }

   fun check(id: Int){
        val dataDao = MovieDatabase.getInstance(getApplication())!!.movieDao()
        dataDao.checkMovie(id)
        getAllMoviePopular()
    }



}



