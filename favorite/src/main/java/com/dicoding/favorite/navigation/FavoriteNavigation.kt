package com.dicoding.favorite.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dicoding.favorite.ui.screen.DetailScreen
import com.dicoding.favorite.ui.screen.FavoriteScreen
import com.dicoding.favorite.ui.viewmodel.FavoriteViewModel
import com.dicoding.ui.navigation.Screen

@Composable
fun FavoriteNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewmodel: FavoriteViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Favorite.route) {
        composable(Screen.Favorite.route) {
            FavoriteScreen(
                modifier = modifier,
                viewModel = viewmodel,
                onCardClick = { artId, isFavorite ->
                    navController.navigate(Screen.Detail.createRoute(artId, isFavorite))
                },
                moveToMain = {
                    navController.navigate(Screen.Main.route)
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = Screen.Detail.arguments
        ) {
            val artId = it.arguments?.getString("artId") ?: ""
            LaunchedEffect(artId) {
                viewmodel.clearArt()
                viewmodel.getArtWork(artId)
            }
            DetailScreen(
                modifier = modifier,
                viewModel = viewmodel,
                onBackClick = {
                    navController.navigateUp()
                },
                onFavoriteClick = {
                    viewmodel.toggleFavorite(artId)
                }
            )
        }
        composable(Screen.Main.route) {
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                (context as? Activity)?.finish()
            }
        }
    }

}