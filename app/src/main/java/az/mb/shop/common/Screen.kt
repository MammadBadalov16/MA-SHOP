package az.mb.shop.common

sealed class Screen(val route: String){
    object Home: Screen(route = "home_screen")
    object SignIn: Screen(route = "sign_in")
    object SignUp: Screen(route = "sign_up")
}
