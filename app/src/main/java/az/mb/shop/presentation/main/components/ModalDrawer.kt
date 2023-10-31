package az.mb.shop.presentation.main.components

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import az.mb.shop.navigation.navigation_items.drawerNavigationScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawer(
    clickIndex: (index: Int) -> Unit,
    clickItemId: (index: Int) -> Unit,
    selectedItem: State<Int> = mutableIntStateOf(1)
) {

    Log.e("s12345", selectedItem.value.toString())
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }


    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))
        drawerNavigationScreens.forEachIndexed { index, item ->
            Log.e("s123", index.toString())

            NavigationDrawerItem(
                label = { Text(text = item.title) },
                selected = index == selectedItemIndex,
                onClick = {
                    if (index != 4) {
                        selectedItemIndex = index
                        clickIndex(index)
                        clickItemId(item.id)
                    }
                },
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

    var selectBottomNavItem = drawerNavigationScreens
        .indexOf(drawerNavigationScreens
            .find { it.id == selectedItem.value })
    Log.e("12345", selectBottomNavItem.toString())
    selectedItemIndex = selectBottomNavItem

}

@OptIn(ExperimentalMaterial3Api::class)
fun drawerNavigate(
    navController: NavController,
    route: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    navController.popBackStack()
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id)
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



