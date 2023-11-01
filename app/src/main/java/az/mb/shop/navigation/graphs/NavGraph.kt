package az.mb.shop.navigation.graphs

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import az.mb.shop.navigation.Screen
import az.mb.shop.presentation.cart.CartScreen
import az.mb.shop.presentation.favorites.FavoritesScreen
import az.mb.shop.presentation.home.HomeScreen
import az.mb.shop.presentation.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
    drawerState: DrawerState
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(drawerState = drawerState)
        }
        composable(route = Screen.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = Screen.Cart.route) {
            CartScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
        composable(route = Screen.Settings.route) {
        }
    }
}