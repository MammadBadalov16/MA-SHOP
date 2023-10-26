package az.mb.shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import az.mb.shop.presentation.cart.CartScreen
import az.mb.shop.presentation.category.CategoryScreen
import az.mb.shop.presentation.favorites.FavoritesScreen
import az.mb.shop.presentation.home.HomeScreen
import az.mb.shop.presentation.product.ProductScreen
import az.mb.shop.presentation.profile.ProfileScreen

@Composable
fun SetupHomeNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = BottomNavigationItem.Home.route
    ) {
        composable(route = BottomNavigationItem.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNavigationItem.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = BottomNavigationItem.Cart.route) {
            CartScreen()
        }
        composable(route = BottomNavigationItem.Profile.route) {
            ProfileScreen()
        }
    }
}