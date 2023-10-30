package az.mb.shop.presentation.main.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import az.mb.shop.navigation.Screen
import az.mb.shop.navigation.graphs.MyNavGraph
import az.mb.shop.navigation.navigation_items.DrawerNavigationItem
import az.mb.shop.navigation.navigation_items.NavigationItem
import az.mb.shop.navigation.navigation_items.bottomNavigationScreens
import az.mb.shop.navigation.navigation_items.drawerNavigationScreens
import kotlinx.coroutines.CoroutineScope

@Composable
fun BottomNavigationBar(
    navController: NavController,
    clickItemId: (index: Int) -> Unit,
    selectedItemId: State<Int> = mutableIntStateOf(1)
) {


    var bnSelectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    var selectBottomNavItem =
        bottomNavigationScreens.indexOf(bottomNavigationScreens.find { it.id == selectedItemId.value })
    bnSelectedItemIndex = selectBottomNavItem


    NavigationBar {
        bottomNavigationScreens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == bnSelectedItemIndex,
                onClick = {
                    bnSelectedItemIndex = index
                    navigate(navController = navController, route = item.route)
                    clickItemId(item.id)
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


private fun navigate(
    navController: NavController,
    route: String,
) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id)
        launchSingleTop = true
    }
}