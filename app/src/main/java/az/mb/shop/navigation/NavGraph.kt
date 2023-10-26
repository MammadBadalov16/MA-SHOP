package az.mb.shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import az.mb.shop.presentation.home.HomeScreen
import az.mb.shop.presentation.signIn.SignInScreen
import az.mb.shop.presentation.signup.SignUpScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    lateinit var navHostController: NavHostController

    NavHost(
        navController = navController,
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

        composable(route = Screen.Home.route) {
            navHostController = rememberNavController()
            SetupHomeNavGraph(navController = navHostController)
        }
    }
}