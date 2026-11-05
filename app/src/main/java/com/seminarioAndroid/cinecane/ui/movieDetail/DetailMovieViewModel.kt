package com.seminarioAndroid.cinecane.ui.movieDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.seminarioAndroid.cinecane.R
import com.seminarioAndroid.cinecane.app.CineCaneApplication
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDetailDto
import com.seminarioAndroid.cinecane.data.remote.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailMovieViewModel (
    val repository : MovieRepository,
    private val movieId : Int,
    private val context : Application
): ViewModel(){

    /**
     * Los posibles estados de renderizado de la pantalla son: cargando, pelicula encontrada,
     * pelicula no encontrada o mensaje de error.
     * Se utilizan 4 variables privadas para manejar cada fase del estado, siguiendo la misma
     * logica utilizada en HomeViewModel.
     * */

    private val _pelicula = MutableStateFlow<MovieDetailDto?>(null)
    val pelicula = _pelicula.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading = _isLoading.asStateFlow()

    private val _exeption = MutableStateFlow<String?>(null)
    val exeption = _exeption.asStateFlow()

    private val _noEncontrada = MutableStateFlow<Boolean>(false)
    val noEncontrada = _noEncontrada.asStateFlow()


    init {
        cargarPelicula()
    }


    fun cargarPelicula(){
        viewModelScope.launch {
            _isLoading.value = true
            _exeption.value = null
            _noEncontrada.value = false

            try {
                _pelicula.value = repository.getPelicula(movieId)

            }catch (e : HttpException) {
                if(e.code() == 404){
                    _noEncontrada.value = true
                }else{
                    _exeption.value = context.getString(R.string.dataExeption)
                }

            }catch (e : IOException) {
                _exeption.value = context.getString(R.string.connectionExeption)

            }finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Define que necesita el viewModel para ser creado, incluyendo el id de la pelicula
     * que llega por navegacion.
     */

    companion object{
        fun Factory(movieId : Int): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as CineCaneApplication
                DetailMovieViewModel(app.movieRepository, movieId, app)
            }

        }
    }

}
