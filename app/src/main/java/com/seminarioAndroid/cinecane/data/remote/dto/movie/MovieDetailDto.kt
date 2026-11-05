package com.seminarioAndroid.cinecane.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

data class MovieDetailDto (
    val id : Int,
    val title : String,
    @SerializedName("poster_path")
    val posterPath : String?,
    val overview : String,
    val genres : List<GenreDto>,
    @SerializedName("vote_average")
    val voteAverage : Double,
    @SerializedName("vote_count")
    val voteCount : Int,
    @SerializedName("release_date") val releaseDate: String?,
    val runtime: Int?
)
