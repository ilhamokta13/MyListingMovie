package com.example.mylistingmovie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class DetailMovie(

    val image: String,
    val title: String,
    val media_type: String,
    val date: String,
    val overview: String,
    val id : String
) : Parcelable
