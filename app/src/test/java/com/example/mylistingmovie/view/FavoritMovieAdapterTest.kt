package com.example.mylistingmovie.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.mylistingmovie.database.FavoriteMovie
import com.example.mylistingmovie.database.MovieDatabase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
class FavoritMovieAdapterTest {
    @MockK
    private lateinit var context: Context

    @MockK
    private lateinit var movieList: List<FavoriteMovie>

    @MockK
    private lateinit var movieDatabase: MovieDatabase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetItemCount() {
        every { movieList.size } returns 5

        val adapter = FavoritMovieAdapter(context, movieList)
        val itemCount = adapter.itemCount

        assertEquals(5, itemCount)
    }


}
