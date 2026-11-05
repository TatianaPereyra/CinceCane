package com.seminarioAndroid.cinecane.data.remote.repository

import com.seminarioAndroid.cinecane.data.remote.api.TmdbApiService
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDetailDto
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto

class MovieRepositoryImp (
    val movieService : TmdbApiService
) : MovieRepository{

    override suspend fun getPopulares() : List<MovieDto> {
        return movieService.getPopulares().results
    }

    override suspend fun getPelicula(id : Int) : MovieDetailDto {
        return movieService.getPelicula(id)
    }

}