package com.example.mylistingmovie.network

import com.example.mylistingmovie.model.ListMovie
import retrofit2.Call
import retrofit2.http.GET

interface RestfulApi {

    @GET("3/movie/634649/recommendations?api_key=7f40338572c7bcecdd056ae5622e950d&language=en-US&page=1")
    fun allMoviesRecom(): Call<ListMovie>
}