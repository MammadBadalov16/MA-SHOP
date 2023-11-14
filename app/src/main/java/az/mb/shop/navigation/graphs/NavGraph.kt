package az.mb.shop.navigation.graphs

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import az.mb.shop.navigation.Screen
import az.mb.shop.presentation.cart.CartScreen
import az.mb.shop.presentation.favorites.FavoritesScreen
import az.mb.shop.presentation.home.HomeScreen
import az.mb.shop.presentation.main.MainScreen
import az.mb.shop.presentation.product.ProductScreen
import az.mb.shop.presentation.profile.ProfileScreen
import az.mb.shop.presentation.signIn.SignInScreen
import az.mb.shop.presentation.signup.SignUpScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
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