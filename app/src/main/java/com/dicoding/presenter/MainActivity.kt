package com.dicoding.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dicoding.presenter.viewmodel.MainViewModel
import com.dicoding.ui.component.BottomBar
import com.dicoding.ui.screen.navigation.MainAndFavoriteNavigationStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Surface {
                Scaffold(
                    bottomBar = {
                        BottomBar(navController = navController)
                    }
                ) { paddingValues ->
                    MainAndFavoriteNavigationStack(
                        modifier = Modifier
                            .padding(paddingValues),
                        navController = navController,
                        viewModel = mainViewModel,
                    )
                }
            }
        }
    }
}

