package az.mb.shop.presentation.main.components

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
import az.mb.shop.navigation.bottomNavigationScreens

@Composable
fun BottomNavigationBar(
    navController: NavController,
) {

    var bnSelectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        bottomNavigationScreens.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = bnSelectedItemIndex == index,
                onClick = {
                    bnSelectedItemIndex = index

                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
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