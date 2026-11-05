package com.seminarioAndroid.cinecane.data.remote.api

import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDetailDto
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.themoviedb.org/3/";

interface TmdbApiService {

    @GET("movie/popular")
    suspend fun getPopulares () : MovieResponseDto;

    @GET("movie/{movieId}")
    suspend fun getPelicula (@Path("movieId") movieId : Int) : MovieDetailDto;

}