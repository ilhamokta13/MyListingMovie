@file:Suppress("FunctionName", "unused")

package com.example.mylistingmovie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

    @Database(entities = [FavoriteMovie::class], version = 1)
    abstract class MovieDatabase : RoomDatabase() {

        abstract fun movieDao() : FavoriteMovieDao

        companion object{
            private var INSTANCE : MovieDatabase? = null

            fun getInstance(context : Context):MovieDatabase? {
                if (INSTANCE == null){
                    synchronized(MovieDatabase::class){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MovieDatabase::class.java,"favoritmovie.db").build()
                    }
                }
                return INSTANCE
            }

            fun destroyInstance(){
                INSTANCE = null
            }
        }

    }
