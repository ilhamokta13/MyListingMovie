package com.example.mylistingmovie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylistingmovie.model.ListMovie
import com.example.mylistingmovie.model.Result
import com.example.mylistingmovie.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var livedatamovie : MutableLiveData<List<Result>> = MutableLiveData()

    fun getlivedatamovie(): MutableLiveData<List<Result>> {
        return livedatamovie
    }

    fun getAllMoviesRecom(){

        ApiClient.instance.allMoviesRecom().enqueue(object : Callback<ListMovie>{
            override fun onResponse(call: Call<ListMovie>, response: Response<ListMovie>) {

                livedatamovie.value = response.body()?.results



            }

            override fun onFailure(call: Call<ListMovie>, t: Throwable) {

                Log.d("Tag",t.message.toString())

            }

        })

    }


}