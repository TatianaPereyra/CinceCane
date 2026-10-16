package com.seminarioAndroid.cinecane.viewmodel

import android.net.http.HttpException
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto
import com.seminarioAndroid.cinecane.data.remote.repository.MovieRepository
import kotlinx.coroutines.coroutineScope
import java.io.IOException

class HomeViewModel(
    val repository: MovieRepository
) {

    //Comienzo mi estado con una lista vacia
    var state : List<MovieDto> = emptyList()

    /**init {

        try {

            HomeViewModel.launch(
                //muestro animacion de carga ,

                //busco las peliculas
                coroutineScope {
                   state = repository.getPopulares();
                }
            )

            //cuando finaliza la co-rutina devuelvo el estado final (la lista)

            if(state.isEmpty()){
               //devuelvo un composable con un mensaje de que no hay peliculas populares
            }

            //retirno la lista de peliculas
            return state


        }catch (e : IOException){
            //retorno una funcion composable que muestre el error

        }catch (e : HttpException){
            //retorno una funcion composable que muestre el error de conexion
        }

    }*/

}