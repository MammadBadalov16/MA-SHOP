package az.mb.shop.navigation

sealed class Screen(val route: String) {
    object SignIn : Screen(route = "sign_in_screen")
    object SignUp : Screen(route = "sign_up_screen")
    object Home : Screen(route = "home_screen")
    object Profile : Screen(route = "profile_screen")
    object Favorites : Screen(route = "favorites_screen")
    object Cart : Screen(route = "cart_screen")
    object Settings : Screen(route = "settings_screen")
    object SignOut : Screen(route = "sign_out")

}
