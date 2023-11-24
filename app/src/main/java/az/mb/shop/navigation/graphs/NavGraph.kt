package az.mb.shop.navigation.graphs

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import az.mb.shop.navigation.Screen
import az.mb.shop.presentation.cart.CartScreen
import az.mb.shop.presentation.favorites.FavoritesScreen
import az.mb.shop.presentation.home.HomeScreen
import az.mb.shop.presentation.product.ProductScreen
import az.mb.shop.presentation.profile.ProfileScreen

@Composable
fun MyNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.background(Color.White),
        enterTransition = {
            fadeIn(animationSpec = tween(700))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(700))
        }
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                drawerState = drawerState,
                navController = navController,
            )
        }
        composable(
            enterTransition = {
                EnterTransition.None
                fadeIn(animationSpec = tween(200))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            },
            route = Screen.Product.route + "/{productId}"
        ) {
            ProductScreen(navController = navController)
        }
        composable(route = Screen.Favorites.route) {
            FavoritesScreen(navController = navController)
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