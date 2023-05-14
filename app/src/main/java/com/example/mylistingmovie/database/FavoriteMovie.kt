package com.example.mylistingmovie.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class FavoriteMovie(
    @PrimaryKey
    val id : Int?,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "image")
    var image : String

):Parcelable
