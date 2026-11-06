package com.seminarioAndroid.cinecane.data.remote.api

import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDetailDto
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/";

interface TmdbApiService {

    @GET("movie/popular")
    suspend fun getPopulares (@Query("language") language : String) : MovieResponseDto;

    @GET("movie/{movieId}")
    suspend fun getPelicula (
        @Path("movieId") movieId : Int,
        @Query("language") language : String
    ) : MovieDetailDto;

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("language") language: String
    ): MovieResponseDto;

}