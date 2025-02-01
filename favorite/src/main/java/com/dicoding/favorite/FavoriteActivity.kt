package com.dicoding.favorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.dicoding.core.di.FavoriteModuleDependencies
import com.dicoding.favorite.di.DaggerFavoriteComponent
import com.dicoding.favorite.navigation.FavoriteNavigation
import com.dicoding.favorite.ui.viewmodel.FavoriteViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val entryPoint = EntryPointAccessors.fromApplication(
            applicationContext,
            FavoriteModuleDependencies::class.java
        )
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                entryPoint
            )
            .build()
            .inject(this)

        setContent {
            BackHandler {
                finish()
            }
            val navController = rememberNavController()
            FavoriteNavigation(
                navController = navController,
                viewmodel = viewModel,
            )
        }
    }
}