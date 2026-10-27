package com.seminarioAndroid.cinecane.UI.home

import android.app.Application
import retrofit2.HttpException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.seminarioAndroid.cinecane.R
import com.seminarioAndroid.cinecane.app.CineCaneApplication
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto
import com.seminarioAndroid.cinecane.data.remote.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(
    val repository: MovieRepository,
    private val context : Application
) : ViewModel() {


    /**
     * Los posibles estados de renderizado de la pantalla son: cargando, lista de peliculas o
     * mensaje de error.
     * Se utilizan 3 variables privadas para manejar cada fase del estado.
     * Cada variable, tiene su version publica para ser accedida desde el composable
     * que renderiza MainActivity.
     *
     * Se utiliza una corrutina para esperar el resultado de la suspend fun del repository
     * que se encarga de traer las peliculas.
     * */

    private val _peliculas = MutableStateFlow<List<MovieDto>>(emptyList())
    val peliculas = _peliculas.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading = _isLoading.asStateFlow()

    private val _exeption = MutableStateFlow<String?>(null)
    val exeption = _exeption.asStateFlow();


    init {
        cargarPeliculas()
    }


    fun cargarPeliculas(){
        viewModelScope.launch {
            _isLoading.value = true
            _exeption.value = null

            try {
                _peliculas.value = repository.getPopulares()

            }catch (e : IOException) {
                _exeption.value = context.getString(R.string.connectionExeption)

            }catch (e : HttpException){
                _exeption.value = context.getString(R.string.dataExeption)

            }finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Define que necesita el viewModel para ser creado y que android lo pueda pasar por
     * parametros a la hora de crearlo
     */

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as CineCaneApplication
                HomeViewModel(app.movieRepository, app)
            }

        }
    }

}