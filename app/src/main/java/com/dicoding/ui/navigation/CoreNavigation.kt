package com.dicoding.ui.navigation

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dicoding.ui.screen.DetailScreen
import com.dicoding.ui.screen.MainScreen
import com.dicoding.ui.viewmodel.MainViewModel

@Composable
fun CoreNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel,
    snackBarHostState: SnackbarHostState
) {

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(
                modifier = modifier,
                viewModel = viewModel,
                onCardClick = { artId, isFavorite ->
                    navController.navigate(Screen.Detail.createRoute(artId, isFavorite))
                },
                moveToFavorite = {
                    navController.navigate(Screen.Favorite.route)
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = Screen.Detail.arguments
        ) {
            val artId = it.arguments?.getString("artId") ?: ""
            LaunchedEffect(artId) {
                viewModel.clearArt()
                viewModel.getArtWork(artId)
            }
            DetailScreen(
                modifier = modifier,
                viewModel = viewModel,
                onBackClick = {
                    navController.navigateUp()
                },
                onFavoriteClick = {
                    viewModel.toggleFavorite(artId)
                }
            )
        }

        composable(Screen.Favorite.route) {
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                try {
                    Intent(context, Class.forName("com.dicoding.favorite.FavoriteActivity"))
                        .also {
                            context.startActivity(it)
                        }
                    navController.popBackStack()
                } catch (
                    e: ClassNotFoundException
                ) {
                    // i want to send message to pop back stack or i think i want to make some information display by toas
                    snackBarHostState.showSnackbar(
                        message = "Favorite feature is not available at the moment.",
                        actionLabel = "OK"
                    )
                    navController.popBackStack()
                }
            }
            Box(Modifier.fillMaxSize())
            {
                Text(
                    text = "404",
                    modifier = Modifier.align(Alignment.Center)
                    , fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}
