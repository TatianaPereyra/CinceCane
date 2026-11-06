package com.seminarioAndroid.cinecane.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seminarioAndroid.cinecane.R
import com.seminarioAndroid.cinecane.ui.composables.EnCANEzado
import com.seminarioAndroid.cinecane.ui.composables.HandleError
import com.seminarioAndroid.cinecane.ui.composables.Loading
import com.seminarioAndroid.cinecane.ui.composables.ListaDePeliculasVertical

@Composable
fun SearchView(
    viewModel: SearchViewModel,
    onMovieClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val peliculas by viewModel.peliculas.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val exeption by viewModel.exeption.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Encabezado con botón de volver
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            EnCANEzado()
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = colorResource(R.color.textColor)
                )
            }
        }

        // Barra de busqueda con bordes redondeados estilo card
        OutlinedTextField(
            value = query,
            onValueChange = { viewModel.onQueryChanged(it) },
            placeholder = { Text(stringResource(R.string.search_hint)) },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = colorResource(R.color.secondTextColor))
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onQueryChanged("") }) {
                        Icon(Icons.Default.Clear, contentDescription = null, tint = colorResource(R.color.secondTextColor))
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    viewModel.searchMovies(query)
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.primaryColor),
                unfocusedContainerColor = colorResource(R.color.primaryColor),
                focusedBorderColor = colorResource(R.color.extraColor),
                unfocusedBorderColor = colorResource(R.color.secondaryColor),
                focusedTextColor = colorResource(R.color.textColor),
                unfocusedTextColor = colorResource(R.color.textColor)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
        )

        // Verificacion del estado
        when {
            isLoading -> Loading()
            exeption != null -> HandleError(
                exeption!!,
                onRetry = { viewModel.searchMovies(query) }
            )
            query.isBlank() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.search_prompt),
                        color = colorResource(R.color.secondTextColor),
                        fontSize = 16.sp
                    )
                }
            }
            peliculas.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.search_empty),
                        color = colorResource(R.color.secondTextColor),
                        fontSize = 16.sp
                    )
                }
            }
            else -> {
                ListaDePeliculasVertical(
                    peliculas,
                    onPeliculaClick = { pelicula -> onMovieClick(pelicula.id) }
                )
            }
        }
    }
}
