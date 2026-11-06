package com.seminarioAndroid.cinecane.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.seminarioAndroid.cinecane.R
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto


// Miniatura de la pelicula renderiza en la lista
@Composable
fun MiniaturaPelicula(
    pelicula: MovieDto,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.primaryColor),
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.5.dp,
                color = colorResource(id = R.color.secondaryColor),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${pelicula.posterPath}",
            contentDescription = "Póster de ${pelicula.title}",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = pelicula.title,
            color = colorResource(id = R.color.textColor),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "★ %.1f".format(pelicula.voteAverage),
            color = colorResource(id = R.color.textColor)
        )
    }
}


//Lista horizontal para pantalla principal - POPULARES
@Composable
fun ListaDePeliculasVertical(
    peliculas: List<MovieDto>,
    onPeliculaClick: (MovieDto) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(peliculas) { pelicula ->
            MiniaturaPelicula(pelicula = pelicula,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onPeliculaClick(pelicula) })
        }
    }
}
