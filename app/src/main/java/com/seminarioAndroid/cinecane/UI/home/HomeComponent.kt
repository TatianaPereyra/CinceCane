package com.seminarioAndroid.cinecane.UI.home

import androidx.compose.foundation.layout.Column
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
import com.seminarioAndroid.cinecane.UI.composables.EnCANEzado
import com.seminarioAndroid.cinecane.UI.composables.HandleError
import com.seminarioAndroid.cinecane.UI.composables.ListaDePeliculasHorizontal
import com.seminarioAndroid.cinecane.UI.composables.Loading
import androidx.compose.runtime.getValue

@Composable
fun HomeView(
    viewModel : HomeViewModel
){

    //Observa el valor del stateFlow y le indica a Compose actualziar la pantalla
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val exeption by viewModel.exeption.collectAsStateWithLifecycle()
    val peliculas by viewModel.peliculas.collectAsStateWithLifecycle()

    Column() {

        EnCANEzado();

        Text(text = stringResource(R.string.welcome_text),
            color = colorResource(R.color.textColor),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp, bottom = 20.dp, top = 10.dp))


        Column() {

            Text(text = stringResource(R.string.popular_text),
                color = colorResource(R.color.textColor),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )

            if(isLoading){
                Loading()

            }else if(exeption != null){
                HandleError(
                    exeption!!,
                    onRetry = { viewModel.cargarPeliculas() });

            }else if(peliculas.isEmpty()){
                HandleError(
                    stringResource(R.string.peliculasEmpty),
                    onRetry = { viewModel.cargarPeliculas() })

            }else{
                ListaDePeliculasHorizontal(peliculas)
            }

        }
    }
}