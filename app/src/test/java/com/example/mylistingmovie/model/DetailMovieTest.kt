package com.example.mylistingmovie.model

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailMovieTest{
    private lateinit var detailMovieTop: DetailMovie

    @Before
    fun setUp() {
        // Persiapan sebelum setiap pengujian dilakukan
        val id = 1
        val imagepath = "path/to/image"
        val title = "Movie Title"
        val date = "2023-05-31"
        val overview = "This is a movie overview"
        val language = "english"
        val popularity = "5.3"

        // Membuat objek DetailMovieTop dengan nilai-nilai yang diberikan
        detailMovieTop = DetailMovie(imagepath,title, date,overview,language,popularity.toDouble(), id)
    }

    @After
    fun tearDown() {
        // Membersihkan setelah setiap pengujian selesai
    }

    @Test
    fun testDetailMovieTop() {
        // Persiapan data yang diperlukan untuk pengujian
        val id = 1
        val imagepath = "path/to/image"
        val title = "Movie Title"
        val date = "2023-05-31"
        val overview = "This is a movie overview"
        val language = "english"
        val popularity = 5.378f


        // Memeriksa apakah nilai-nilai objek DetailMovieTop sesuai dengan yang diharapkan
        assertEquals(id, detailMovieTop.id)
        assertEquals(imagepath, detailMovieTop.image)
        assertEquals(title, detailMovieTop.title)
        assertEquals(date, detailMovieTop.date)
        assertEquals(overview, detailMovieTop.overview)
        assertEquals(language, detailMovieTop.language)
        assertEquals( 0.0010f, 0.0013f, 0.0004f)
    }
}