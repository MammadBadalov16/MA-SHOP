package az.mb.shop.navigation.navigation_items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import az.mb.shop.navigation.Screen


sealed class DrawerNavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : DrawerNavigationItem(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Favorites : DrawerNavigationItem(
        route = Screen.Favorites.route,
        title = "Favorites",
        icon = Icons.Default.Favorite
    )

    object Cart : DrawerNavigationItem(
        route = Screen.Cart.route,
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )

    object Profile : DrawerNavigationItem(
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Settings : DrawerNavigationItem(
        route = Screen.Settings.route,
        title = "Settings",
        icon = Icons.Default.Settings
    )

    object SignOut : DrawerNavigationItem(
        route = Screen.SignOut.route,
        title = "Sign out",
        icon = Icons.Default.ExitToApp
    )
}

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : DrawerNavigationItem(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Favorites : DrawerNavigationItem(
        route = Screen.Favorites.route,
        title = "Favorites",
        icon = Icons.Default.Favorite
    )

    object Cart : DrawerNavigationItem(
        route = Screen.Cart.route,
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )

    object Profile : DrawerNavigationItem(
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.Person
    )
}


val bottomNavigationScreens = listOf(
    NavigationItem.Home,
    NavigationItem.Favorites,
    NavigationItem.Cart,
    NavigationItem.Profile,
)
val drawerNavigationScreens = listOf(
    DrawerNavigationItem.Home,
    DrawerNavigationItem.Profile,
    DrawerNavigationItem.Favorites,
    DrawerNavigationItem.Cart,
    DrawerNavigationItem.Settings,
    DrawerNavigationItem.SignOut,
)
