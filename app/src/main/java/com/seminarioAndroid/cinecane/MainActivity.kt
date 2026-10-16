package com.seminarioAndroid.cinecane

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.seminarioAndroid.cinecane.UI.composables.ListaDePeliculasHorizontal
import com.seminarioAndroid.cinecane.data.remote.api.RetrofitClient
import kotlin.collections.emptyList
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seminarioAndroid.cinecane.UI.composables.EnCANEzado
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var peliculas by remember { mutableStateOf<List<MovieDto>>(emptyList()) }

            LaunchedEffect(Unit) {
                try {
                    peliculas = RetrofitClient.api.getPopulares().results
                } catch (e: Exception) {
                    Log.e("CineCane", "Error al traer películas", e)
                }
            }

            MaterialTheme {
                Column {
                    EnCANEzado();

                    Text(text = stringResource(R.string.welcome_text),
                         color = colorResource(R.color.textColor),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 20.dp, bottom = 20.dp, top = 10.dp))


                    //ACA IRIA LA BARRA DE BUSQUEDA


                    Column() {

                        Text(text = stringResource(R.string.popular_text),
                             color = colorResource(R.color.textColor),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(20.dp)
                        )

                        ListaDePeliculasHorizontal(peliculas)

                    }

                }
            }
        }
    }
}
