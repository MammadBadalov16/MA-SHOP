package az.mb.shop.navigation

sealed class Screen(val id: Int, val route: String) {
    object SignIn : Screen(id = 1, route = "sign_in_screen")
    object SignUp : Screen(id = 2, route = "sign_up_screen")
    object Home : Screen(id = 3, route = "home_screen")
    object Profile : Screen(id = 4, route = "profile_screen")
    object Favorites : Screen(id = 5, route = "favorites_screen")
    object Cart : Screen(id = 6, route = "cart_screen")
    object Settings : Screen(id = 7, route = "settings_screen")
    object SignOut : Screen(id = 8, route = "sign_out")

}
