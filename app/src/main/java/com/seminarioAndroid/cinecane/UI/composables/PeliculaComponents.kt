package com.seminarioAndroid.cinecane.UI.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
fun MiniaturaPelicula(pelicula: MovieDto) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(350.dp)
            .background(
                color = colorResource(id = R.color.primaryColor),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${pelicula.posterPath}",
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
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.height(50.dp)
        )

        Text(
            text = "★ %.1f".format(pelicula.voteAverage),
            color = colorResource(id = R.color.textColor)
        )
    }
}


//Lista horizontal para pantalla principal - POPULARES
@Composable
fun ListaDePeliculasHorizontal(
    peliculas: List<MovieDto>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(id = R.color.secondaryColor)
            )
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(peliculas) { pelicula ->
            MiniaturaPelicula(pelicula)
        }
    }
}
