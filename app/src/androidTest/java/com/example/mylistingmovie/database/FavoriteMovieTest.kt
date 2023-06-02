package com.example.mylistingmovie.database

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FavoriteMovieTest{

    private lateinit var movieFavorit: FavoriteMovie
    @Before
    fun setUp() {
        // Persiapan sebelum setiap pengujian dilakukan
        movieFavorit = FavoriteMovie(
            id = 1,
            title = "Hey Tayo",
            release = "2017-05-31",
            image = "https://example.com/movie.jpg",
            overview = "Movie The Best",
            language = "ind",
            popularity = "3"
        )
    }

    @After
    fun tearDown() {
        // Membersihkan setelah setiap pengujian selesai
    }

    @Test
    fun testMovieFavorit() {
        // Memeriksa apakah nilai yang diatur sesuai dengan yang diharapkan
        assertEquals(1, movieFavorit.id)
        assertEquals("Hey Tayo", movieFavorit.title)
        assertEquals("2017-05-31", movieFavorit.release)
        assertEquals("https://example.com/movie.jpg", movieFavorit.image)
        assertEquals("Movie The Best", movieFavorit.overview)
        assertEquals("ind", movieFavorit.language)
        assertEquals("3", movieFavorit.popularity)
    }
}