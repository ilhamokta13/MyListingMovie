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
            title = "The Movie",
            release = "2023-05-31",
            image = "https://example.com/movie.jpg",
            overview = "This is a great movie!",
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
        assertEquals("The Movie", movieFavorit.title)
        assertEquals("2023-05-31", movieFavorit.release)
        assertEquals("https://example.com/movie.jpg", movieFavorit.image)
        assertEquals("This is a great movie!", movieFavorit.overview)
        assertEquals("ind", movieFavorit.language)
        assertEquals("3", movieFavorit.popularity)
    }
}