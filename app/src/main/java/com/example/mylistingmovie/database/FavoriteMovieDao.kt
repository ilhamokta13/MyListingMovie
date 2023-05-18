package com.example.mylistingmovie.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM FavoriteMovie")
    fun getAllFilmFavorites() : LiveData<List<FavoriteMovie>>

    @Insert
    suspend fun insertFilmFavorites(filmFavorites: FavoriteMovie) : Long

    @Delete
    suspend fun deleteFilmFavorites(filmFavorites: FavoriteMovie) : Int

}