package com.seminarioAndroid.cinecane.ui.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.seminarioAndroid.cinecane.R
import com.seminarioAndroid.cinecane.app.CineCaneApplication
import com.seminarioAndroid.cinecane.app.DeviceLocale
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto
import com.seminarioAndroid.cinecane.data.remote.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SearchViewModel(
    val repository: MovieRepository,
    private val context: Application
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _peliculas = MutableStateFlow<List<MovieDto>>(emptyList())
    val peliculas = _peliculas.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _exeption = MutableStateFlow<String?>(null)
    val exeption = _exeption.asStateFlow()

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
        if (newQuery.isBlank()) {
            _peliculas.value = emptyList()
            _exeption.value = null
            _isLoading.value = false
        } else {
            searchMovies(newQuery)
        }
    }

    fun searchMovies(searchQuery: String) {
        if (searchQuery.isBlank()) return
        viewModelScope.launch {
            _isLoading.value = true
            _exeption.value = null

            try {
                _peliculas.value = repository.searchMovies(searchQuery, DeviceLocale.languageTag())
            } catch (e: IOException) {
                _exeption.value = context.getString(R.string.connectionExeption)
            } catch (e: HttpException) {
                _exeption.value = context.getString(R.string.dataExeption)
            } finally {
                _isLoading.value = false
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as CineCaneApplication
                SearchViewModel(app.movieRepository, app)
            }
        }
    }
}
