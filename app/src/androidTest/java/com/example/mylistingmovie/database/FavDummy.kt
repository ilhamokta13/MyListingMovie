package com.example.mylistingmovie.database

object FavDummy {

    fun generateDummyFavourite():List<FavoriteMovie>{
        val favoriteList = ArrayList<FavoriteMovie>()
        for (i in 0..5){
            val favorite =FavoriteMovie(
                id = i,
                title = "title $i",
                release = "2022-03-0$i",
                image = "/$i.jpg",
                overview = "overview $i",
                language = "language $i",
                popularity = "popularity $i"
            )
            favoriteList.add(favorite)
        }
        return favoriteList
    }
}