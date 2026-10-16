package com.seminarioAndroid.cinecane.data.remote.api

import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieResponseDto
import retrofit2.http.GET

private const val BASE_URL = "https://api.themoviedb.org/3/";

interface TmdbApiService {

    //Obtengo todas las peliculas populares. Devuelve lista de peliculas
    @GET("movie/popular")
    suspend fun getPopulares () : MovieResponseDto;



}