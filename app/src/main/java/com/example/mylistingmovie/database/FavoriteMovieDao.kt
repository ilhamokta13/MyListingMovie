package com.example.mylistingmovie.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDao {
    @Insert
    fun addToFavorit(favoritMovie : FavoriteMovie):Long

    @Query("SELECT * FROM FavoriteMovie")
    fun getFavoritMovie() : List<FavoriteMovie>


    @Query("SELECT count(*) FROM FavoriteMovie WHERE FavoriteMovie.id = :id")
    fun checkMovie(id: Int) : Int

}