package com.example.mylistingmovie.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class MovieDatabaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var database:MovieDatabase
    private lateinit var moviedao: FavoriteMovieDao
    private val sampleFavorite = FavDummy.generateDummyFavourite()[0]

    @Before
    fun initDb(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).build()
        moviedao = database.movieDao()
    }

    @After
    fun closeDb() = database.close()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun saveFavouriteSuccess() = runTest {
        moviedao.addToFavorit(sampleFavorite)
        val actualFavorite = moviedao.getFavoritMovie()
        assertEquals(sampleFavorite.title, actualFavorite[0].title)
        println(sampleFavorite.title)
        println(actualFavorite[0].title)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteFav() = runTest {
        moviedao.addToFavorit(sampleFavorite)
        moviedao.deleteFilmFavorites(sampleFavorite)
        val actualMovie = moviedao.getFavoritMovie()
        assertTrue(actualMovie.isEmpty())
    }
}
