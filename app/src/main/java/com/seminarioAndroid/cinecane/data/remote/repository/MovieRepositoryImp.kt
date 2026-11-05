package com.seminarioAndroid.cinecane.data.remote.repository

import com.seminarioAndroid.cinecane.data.remote.api.TmdbApiService
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDetailDto
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto

class MovieRepositoryImp (
    val movieService : TmdbApiService
) : MovieRepository{

    override suspend fun getPopulares(language : String) : List<MovieDto> {
        return movieService.getPopulares(language).results
    }

    override suspend fun getPelicula(id : Int, language : String) : MovieDetailDto {
        return movieService.getPelicula(id, language)
    }

}