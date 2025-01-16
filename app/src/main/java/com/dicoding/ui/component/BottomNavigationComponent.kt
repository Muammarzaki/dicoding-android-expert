package com.dicoding.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.presenter.Screen
import com.dicoding.ui.theme.ColArtsTheme

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

@Composable
fun BottomBar(modifier: Modifier = Modifier, navController: NavController) {
    NavigationBar(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Outlined.Home,
                screen = Screen.Main
            ),
            NavigationItem(
                title = "Profile",
                icon = Icons.Outlined.FavoriteBorder,
                screen = Screen.Favorite
            ),
        )
        navigationItems.map {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = false,
                onClick = {
                    navController.navigate(it.screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    ColArtsTheme {
        BottomBar(
            navController = rememberNavController()
        )
    }
}