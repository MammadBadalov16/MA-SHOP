package az.mb.shop.presentation.main.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import az.mb.shop.navigation.Screen
import az.mb.shop.navigation.graphs.MyNavGraph
import az.mb.shop.navigation.navigation_items.DrawerNavigationItem
import az.mb.shop.navigation.navigation_items.bottomNavigationScreens
import kotlinx.coroutines.CoroutineScope

@Composable
fun BottomNavigationBar(
    navController: NavController,
    clickedProfile: () -> Unit
    /*
        changeSelectedItem: () -> Unit
    */
) {

    var bnSelectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        bottomNavigationScreens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = bnSelectedItemIndex == index,
                onClick = {
                    bnSelectedItemIndex = index
                    if (clickedProfile(item.route)){
                        clickedProfile()
                        return@NavigationBarItem
                    }

                    navigate(navController = navController, route = item.route)
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = if (index == bnSelectedItemIndex) {
                            item.icon
                        } else item.icon,
                        contentDescription = item.title
                    )

                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
private fun navigate(
    navController: NavController,
    route: String,
) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id)
        launchSingleTop = true
    }
}

private fun clickedProfile(route: String): Boolean {
    if (route == Screen.Profile.route)
        return true

    return false
}