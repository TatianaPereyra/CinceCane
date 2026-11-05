package com.seminarioAndroid.cinecane.ui.movieDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.seminarioAndroid.cinecane.R
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDetailDto
import com.seminarioAndroid.cinecane.ui.composables.EnCANEzado
import com.seminarioAndroid.cinecane.ui.composables.HandleError
import com.seminarioAndroid.cinecane.ui.composables.Loading


@Composable
fun DetailMovieView(
    viewModel: DetailMovieViewModel,
    onBack: () -> Unit
) {
    //Observa el valor de los stateFlow y le indica a Compose actualizar la pantalla
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val exeption by viewModel.exeption.collectAsStateWithLifecycle()
    val pelicula by viewModel.pelicula.collectAsStateWithLifecycle()
    val noEncontrada by viewModel.noEncontrada.collectAsStateWithLifecycle()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.primaryColor)),
            contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> Loading()
            noEncontrada -> HandleError(
                stringResource(R.string.peliculaNoEncontrada),
                onRetry = { viewModel.cargarPelicula() }
            )
            exeption != null -> HandleError(exeption!!, onRetry = { viewModel.cargarPelicula() })
            pelicula != null -> DetallePelicula(pelicula!!)
        }

        // El botón de volver queda siempre visible, encima del contenido
        BotonAtras(onBack = onBack, modifier = Modifier.align(Alignment.TopStart))
    }
}

@Composable
private fun BotonAtras(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onBack,
        modifier = modifier
            .statusBarsPadding()
            .padding(12.dp)
            .size(40.dp)
            .background(
                color = colorResource(R.color.primaryColor).copy(alpha = 0.6f),
                shape = CircleShape
            )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
            contentDescription = stringResource(R.string.back),
            tint = colorResource(R.color.textColor)
        )
    }
}

@Composable
private fun DetallePelicula(
    pelicula: MovieDetailDto
) {
    // Línea de detalles bajo el título: "2023  •  1h 46min". Se omiten los datos que no vengan.
    val detalles = listOfNotNull(
        pelicula.releaseDate?.take(4)?.takeIf { it.isNotBlank() },
        pelicula.runtime?.takeIf { it > 0 }?.let { formatearDuracion(it) }
    ).joinToString("  •  ")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
        ) {
            if (pelicula.posterPath != null) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w780${pelicula.posterPath}",
                    contentDescription = "Póster de ${pelicula.title}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(R.color.buttonColor)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.sinPortada),
                        color = colorResource(R.color.textColor),
                        fontSize = 16.sp
                    )
                }
            }

            // Degrade
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, colorResource(R.color.primaryColor))
                        )
                    )
            )

            // Título y detalles sobre el degrade
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    text = pelicula.title,
                    color = colorResource(R.color.textColor),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                if (detalles.isNotEmpty()) {
                    Text(
                        text = detalles,
                        color = colorResource(R.color.textColor),
                        fontSize = 14.sp
                    )
                }
            }
        }

        // Contenido debajo de la imagen
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "★ %.1f / 10".format(pelicula.voteAverage),
                color = colorResource(R.color.secondaryColor),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            if (pelicula.genres.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    pelicula.genres.forEach { genero ->
                        GeneroEtiqueta(genero.name)
                    }
                }
            }

            Text(
                text = stringResource(R.string.overview),
                color = colorResource(R.color.textColor),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = pelicula.overview.ifBlank { stringResource(R.string.sinopsisNoDisponible) },
                color = colorResource(R.color.textColor),
                fontSize = 16.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Estadistica(
                    titulo = stringResource(R.string.voteAverage),
                    valor = "%.1f".format(pelicula.voteAverage),
                    modifier = Modifier.weight(1f)
                )

                Estadistica(
                    titulo = stringResource(R.string.voteCount),
                    valor = pelicula.voteCount.toString(),
                    modifier = Modifier.weight(1f)
                )

            }

            // Deja lugar para la barra de navegación del sistema
            Spacer(Modifier.navigationBarsPadding())
        }
    }
}

/** Convierte minutos a un texto legible: 106 -> "1h 46min", 45 -> "45min", 120 -> "2h". */
private fun formatearDuracion(minutos: Int): String {
    val horas = minutos / 60
    val resto = minutos % 60
    return when {
        horas == 0 -> "${resto}min"
        resto == 0 -> "${horas}h"
        else -> "${horas}h ${resto}min"
    }
}

@Composable
private fun GeneroEtiqueta(
    nombre: String
) {
    Text(
        text = nombre,
        color = colorResource(R.color.textColor),
        fontSize = 14.sp,
        modifier = Modifier
            .background(
                color = colorResource(R.color.buttonColor),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    )
}

@Composable
private fun Estadistica(
    titulo: String,
    valor: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = colorResource(R.color.secondaryColor),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = valor,
            color = colorResource(R.color.primaryColor),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = titulo,
            color = colorResource(R.color.primaryColor),
            fontSize = 12.sp
        )
    }
}