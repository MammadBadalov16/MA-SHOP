package az.mb.shop.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import az.mb.shop.navigation.Screen
import az.mb.shop.presentation.signIn.SignInScreen
import az.mb.shop.presentation.signup.SignUpScreen

fun NavGraphBuilder.authGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = Screen.SignIn.route
    ) {
        composable(
            route = Screen.SignIn.route
        ) {
            SignInScreen(navController = navController)
        }
        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(navController = navController)
        }
    }
}