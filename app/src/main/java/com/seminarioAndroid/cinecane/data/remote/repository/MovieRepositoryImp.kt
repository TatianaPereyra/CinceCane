package com.seminarioAndroid.cinecane.data.remote.repository

import com.seminarioAndroid.cinecane.data.remote.api.TmdbApiService
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto

//Implementacion de la interface

class MovieRepositoryImp (
    val movieService : TmdbApiService
) : MovieRepository{

    override suspend fun getPopulares() : List<MovieDto> {
        return movieService.getPopulares().results
    }
}