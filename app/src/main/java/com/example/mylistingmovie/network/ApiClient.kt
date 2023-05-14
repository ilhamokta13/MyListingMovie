package com.example.mylistingmovie.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val  BASE_URL ="https://api.themoviedb.org/"
    private const val API_KEY = "7f40338572c7bcecdd056ae5622e950d"

    val instance : RestfulApi by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RestfulApi::class.java)
    }
}