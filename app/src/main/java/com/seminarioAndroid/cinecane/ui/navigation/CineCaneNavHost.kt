package com.seminarioAndroid.cinecane.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.seminarioAndroid.cinecane.ui.home.HomeView
import com.seminarioAndroid.cinecane.ui.home.HomeViewModel
import com.seminarioAndroid.cinecane.ui.movieDetail.DetailMovieView
import com.seminarioAndroid.cinecane.ui.movieDetail.DetailMovieViewModel

//Rutas utilziadas para navegar
private object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{movieId}"

    fun detail(movieId : Int) = "detail/$movieId"
}

/**
 * Define las pantallas de la app y cómo se navega entre ellas.
 *
 * - [Routes.HOME]: lista de películas populares. Al tocar una película se navega al detalle.
 * - [Routes.DETAIL]: detalle de una película. Recibe el id por la ruta (`detail/{movieId}`)
 *   y vuelve a la pantalla anterior con el botón de atrás.
 *
 * Cada pantalla crea su propio ViewModel, y se descarta al salir de ella.
 */
@Composable
fun CineCaneNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        //Declara la navegacion a la ruta home, y crea el viewModel y view correspondiente
        composable(Routes.HOME) {
            val viewModel : HomeViewModel = viewModel(
                factory = HomeViewModel.Factory
            )

            HomeView(
                viewModel = viewModel,
                //Permite que desde un click sobre alguna pelicula se navegue al detalle de la misma
                onMovieClick = { movieId -> navController.navigate(Routes.detail(movieId)) }
            )
        }

        //Declara la navegacion a la ruta de detalle y crea el viewModel y view correspondiente
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Si el id no llegara, se usa 0 (no existe en TMDB)
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            val viewModel : DetailMovieViewModel = viewModel(
                factory = DetailMovieViewModel.Factory(movieId)
            )

            DetailMovieView(
                viewModel = viewModel,
                //Si vuelve atras, vuelve a home
                onBack = { navController.popBackStack() }
            )
        }
    }
}
