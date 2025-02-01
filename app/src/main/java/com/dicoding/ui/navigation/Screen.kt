package com.dicoding.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    data object Main : Screen("main")

    data object Detail : Screen("detail/{artId}/{isFavorite}") {
        fun createRoute(artId: String, isFavorite: Boolean) = "detail/$artId/$isFavorite"
        val arguments = listOf(
            navArgument("artId") { type = NavType.StringType },
            navArgument("isFavorite") { type = NavType.BoolType }
        )
    }

    data object Favorite : Screen("favorite")
}