package com.example.mylistingmovie.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class FavoriteMovie(
    @PrimaryKey
    val id : Int?,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "release")
    var release: String,
    @ColumnInfo(name = "image")
    var image : String

):Parcelable
