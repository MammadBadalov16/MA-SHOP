package az.mb.shop.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import az.mb.shop.presentation.main.MainScreen

@Composable
fun RootNavGraph(navController: NavHostController, startGraph: String = Graph.AUTHENTICATION) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startGraph
    ) {
        authGraph(navController = navController)
        composable(route = Graph.NAV) {
            MainScreen()
        }

    }
}
