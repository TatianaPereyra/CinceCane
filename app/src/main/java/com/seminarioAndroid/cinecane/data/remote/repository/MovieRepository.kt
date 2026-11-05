package com.seminarioAndroid.cinecane.data.remote.repository

import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDetailDto
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto


interface MovieRepository{

    suspend fun getPopulares(language : String) : List<MovieDto>

    suspend fun getPelicula(id : Int, language : String) : MovieDetailDto

}