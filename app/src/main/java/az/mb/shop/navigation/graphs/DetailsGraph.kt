package az.mb.shop.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import az.mb.shop.navigation.Screen
import az.mb.shop.presentation.product.ProductScreen
import az.mb.shop.presentation.profile.ProfileScreen
import az.mb.shop.presentation.signIn.SignInScreen
import az.mb.shop.presentation.signup.SignUpScreen


fun NavGraphBuilder.detailsGraph(
    navController: NavHostController,
    startDestination: String = Screen.Product.route
) {
    navigation(
        route = Graph.DETAILS,
        startDestination = startDestination
    ) {
        composable(
            route = Screen.Product.route + "/{productId}"
        ) {
            ProductScreen(navController = navController)
        }
    }
}