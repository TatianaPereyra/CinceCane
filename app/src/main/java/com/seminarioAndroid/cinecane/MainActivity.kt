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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seminarioAndroid.cinecane.UI.composables.EnCANEzado
import com.seminarioAndroid.cinecane.UI.home.HomeView
import com.seminarioAndroid.cinecane.UI.home.HomeViewModel
import com.seminarioAndroid.cinecane.data.remote.dto.movie.MovieDto
import com.seminarioAndroid.cinecane.data.remote.repository.MovieRepository

class MainActivity () : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.Factory
            )

            HomeView(viewModel = viewModel)
        }
    }
}
