package com.dicoding.presenter

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Favorite : Screen("favorite")
}