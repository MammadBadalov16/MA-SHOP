package az.mb.shop.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object SignIn : Screen(route = "sign_in_screen")
    object SignUp : Screen(route = "sign_up_screen")
    object Product : Screen(route = "product_screen")
    object Category : Screen(route = "category_screen")
}

sealed class BottomNavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavigationItem(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Favorites : BottomNavigationItem(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Default.Favorite
    )

    object Cart : BottomNavigationItem(
        route = "cart",
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )
    object Profile : BottomNavigationItem(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}


val bottomNavigationScreens = listOf(
    BottomNavigationItem.Home,
    BottomNavigationItem.Profile,
    BottomNavigationItem.Cart,
    BottomNavigationItem.Profile,
)