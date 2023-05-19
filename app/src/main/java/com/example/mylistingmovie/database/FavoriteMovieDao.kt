package com.example.mylistingmovie.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDao {
    @Insert
    suspend fun addToFavorit(favoritMovie : FavoriteMovie) : Long

    @Query("SELECT * FROM FavoriteMovie")
    fun getFavoritMovie() : List<FavoriteMovie>

    @Query("SELECT count(*) FROM FavoriteMovie WHERE FavoriteMovie.id = :id")
    fun checkMovie(id: Int) : Int


    @Delete
    suspend fun deleteFilmFavorites(filmFavorites: FavoriteMovie) : Int



}