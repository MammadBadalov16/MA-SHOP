package az.mb.shop.presentation.main.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import az.mb.shop.common.PreferencesManager
import az.mb.shop.navigation.Screen
import az.mb.shop.navigation.navigation_items.bottomNavigationScreens
import az.mb.shop.navigation.navigation_items.drawerNavigationScreens
import az.mb.shop.presentation.ui.theme.darkGrey
import az.mb.shop.presentation.ui.theme.f5
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawer(
    clickItemId: (index: Int) -> Unit,
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    context: Context,
    selectedItemId: State<Int> = mutableIntStateOf(3),
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val selectBottomNavItem =
        drawerNavigationScreens.indexOf(
            drawerNavigationScreens.find { it.id == selectedItemId.value })
    selectedItemIndex = selectBottomNavItem

    ModalDrawerSheet(drawerContainerColor = Color.White) {
        Spacer(modifier = Modifier.height(25.dp))
        DrawerHeader()
        Spacer(modifier = Modifier.height(16.dp))
        drawerNavigationScreens.forEachIndexed { index, item ->
            Log.e("s123", index.toString())

            NavigationDrawerItem(
                label = { Text(text = item.title) },
                selected = index == selectedItemIndex,
                onClick = {
                    clickItemId(item.id)

                    if (index != 4 && index != 5) {

                        selectedItemIndex = index

                        drawerNavigate(
                            navController = navController,
                            route = drawerNavigationScreens[index].route,
                            scope = scope,
                            drawerState = drawerState,
                            context = context
                        )
                    }


                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = darkGrey,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color.Black,
                    selectedContainerColor = f5,
                    unselectedContainerColor = Color.Transparent,
                ),
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.icon
                        } else item.icon, contentDescription = item.title
                    )
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun drawerNavigate(
    navController: NavController,
    route: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    context: Context

) {
    //

    /*  if (route == Screen.SignOut.route)
          PreferencesManager(context).removeAllSharedPreferences()*/


    navController.navigate(route) {
        navController.popBackStack()
        // popUpTo(navController.graph.findStartDestination().id)
        launchSingleTop = true
    }

    closeDrawer(drawerState = drawerState, scope = scope)
}

@OptIn(ExperimentalMaterial3Api::class)
fun closeDrawer(drawerState: DrawerState, scope: CoroutineScope) {
    scope.launch {
        drawerState.close()
    }
}





