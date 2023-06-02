package com.example.mylistingmovie.view

import org.junit.Assert.*

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bumptech.glide.Glide
import com.example.mylistingmovie.databinding.ItemMovieBinding
import com.example.mylistingmovie.model.Result
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import com.example.mylistingmovie.R
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieAdapterTest {
    private lateinit var mockListMovie: List<Result>
    private lateinit var mockViewHolder: MovieAdapter.ViewHolder
    private lateinit var mockBinding: ItemMovieBinding
    private lateinit var adapter: MovieAdapter
    @Before
    fun setup() {
        mockListMovie = mockk()
        mockViewHolder = mockk(relaxed = true)
        mockBinding = mockk(relaxed = true)
        adapter = MovieAdapter(mockListMovie)
    }
    @Test
    fun testGetItemCount() {
        val itemCount = 3
        every { mockListMovie.size } returns itemCount

        val actualItemCount = adapter.itemCount

        assertEquals(itemCount, actualItemCount)
    }
}
