package com.seminarioAndroid.cinecane.app

import android.app.Application
import com.seminarioAndroid.cinecane.UI.home.HomeViewModel
import com.seminarioAndroid.cinecane.data.remote.api.RetrofitClient
import com.seminarioAndroid.cinecane.data.remote.api.TmdbApiService
import com.seminarioAndroid.cinecane.data.remote.repository.MovieRepository
import com.seminarioAndroid.cinecane.data.remote.repository.MovieRepositoryImp

class CineCaneApplication : Application() {
    val movieService : TmdbApiService = RetrofitClient.api
    val movieRepository : MovieRepository = MovieRepositoryImp(movieService)

}