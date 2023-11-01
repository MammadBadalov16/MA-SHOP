package az.mb.shop.presentation.main.components

import android.util.Log
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import az.mb.shop.navigation.navigation_items.bottomNavigationScreens
import az.mb.shop.presentation.ui.theme.darkGrey
import az.mb.shop.presentation.ui.theme.f3

@Composable
fun BottomNavigationBar(
    navController: NavController,
    clickItemId: (index: Int) -> Unit,
    selectedItemId: State<Int> = mutableIntStateOf(3)
) {

    var bnSelectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val selectBottomNavItem =
        bottomNavigationScreens.indexOf(
            bottomNavigationScreens.find { it.id == selectedItemId.value })
    bnSelectedItemIndex = selectBottomNavItem


    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Gray,
    ) {
        bottomNavigationScreens.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = darkGrey,
                    selectedTextColor = Color.Black,
                    indicatorColor = f3
                ),
                selected = index == bnSelectedItemIndex,
                onClick = {
                    bnSelectedItemIndex = index
                    navController.popBackStack()
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