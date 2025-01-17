package com.dicoding.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dicoding.presenter.viewmodel.MainViewModel
import com.dicoding.presenter.Screen
import com.dicoding.ui.screen.FavoriteScreen
import com.dicoding.ui.screen.MainScreen

@Composable
fun MainAndFavoriteNavigationStack(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel
) {

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            MainScreen(modifier, viewModel)
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(modifier, viewModel)
        }
    }
}