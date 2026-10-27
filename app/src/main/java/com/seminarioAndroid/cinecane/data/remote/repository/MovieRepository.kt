package com.seminarioAndroid.cinecane.data.remote.repository

import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto


interface MovieRepository{

    suspend fun getPopulares() : List<MovieDto>


}