package com.seminarioAndroid.cinecane.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seminarioAndroid.cinecane.R
import com.seminarioAndroid.cinecane.ui.composables.EnCANEzado
import com.seminarioAndroid.cinecane.ui.composables.HandleError
import com.seminarioAndroid.cinecane.ui.composables.Loading
import androidx.compose.runtime.getValue
import com.seminarioAndroid.cinecane.ui.composables.ListaDePeliculasVertical

@Composable
fun HomeView(
    viewModel : HomeViewModel,
    onMovieClick : (Int) -> Unit,
    onSearchClick : () -> Unit
){

    //Observa el valor del stateFlow y le indica a Compose actualizar la pantalla
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val exeption by viewModel.exeption.collectAsStateWithLifecycle()
    val peliculas by viewModel.peliculas.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {

        EnCANEzado(onSearchClick = onSearchClick)

        Text(
            text = stringResource(R.string.welcome_text),
            color = colorResource(R.color.textColor),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)
        )

        Text(
            text = stringResource(R.string.popular_text),
            color = colorResource(R.color.textColor),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 12.dp)
        )

        when {
            isLoading -> Loading()
            exeption != null -> HandleError(
                exeption!!,
                onRetry = { viewModel.cargarPeliculas() }
            )
            peliculas.isEmpty() -> HandleError(
                stringResource(R.string.peliculasEmpty),
                onRetry = { viewModel.cargarPeliculas() }
            )
            else -> ListaDePeliculasVertical(
                peliculas,
                onPeliculaClick = { pelicula -> onMovieClick(pelicula.id) }
            )
        }
    }
}
