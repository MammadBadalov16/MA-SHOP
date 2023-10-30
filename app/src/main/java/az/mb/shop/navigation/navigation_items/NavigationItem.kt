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
    val id: Int,
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : DrawerNavigationItem(
        id = Screen.Home.id,
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Favorites : DrawerNavigationItem(
        id = Screen.Favorites.id,
        route = Screen.Favorites.route,
        title = "Favorites",
        icon = Icons.Default.Favorite
    )

    object Cart : DrawerNavigationItem(
        id = Screen.Cart.id,
        route = Screen.Cart.route,
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )

    object Profile : DrawerNavigationItem(
        id = Screen.Profile.id,
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Settings : DrawerNavigationItem(
        id = Screen.Settings.id,
        route = Screen.Settings.route,
        title = "Settings",
        icon = Icons.Default.Settings
    )

    object SignOut : DrawerNavigationItem(
        id = Screen.SignOut.id,
        route = Screen.SignOut.route,
        title = "Sign out",
        icon = Icons.Default.ExitToApp
    )
}

sealed class NavigationItem(
    val id: Int,
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavigationItem(
        id = Screen.Home.id,
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Favorites : NavigationItem(
        id = Screen.Favorites.id,
        route = Screen.Favorites.route,
        title = "Favorites",
        icon = Icons.Default.Favorite
    )

    object Cart : NavigationItem(
        id = Screen.Cart.id,
        route = Screen.Cart.route,
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )

    object Profile : NavigationItem(
        id = Screen.Profile.id,
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
