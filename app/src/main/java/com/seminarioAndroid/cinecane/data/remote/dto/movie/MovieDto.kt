package com.seminarioAndroid.cinecane.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

data class MovieDto (
    val id : Int,
    val title : String,
    @SerializedName("poster_path")
    val posterPath : String?,
    @SerializedName("vote_average")
    val voteAverage : Double
)