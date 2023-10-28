package az.mb.shop.presentation.main.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import az.mb.shop.common.Constants
import az.mb.shop.common.PreferencesManager
import az.mb.shop.navigation.Screen
import az.mb.shop.navigation.navigation_items.drawerNavigationScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawer(
    clickIndex: (index: Int) -> Unit,
) {

    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val context = LocalContext.current.applicationContext

    androidx.compose.material3.ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))
        drawerNavigationScreens.forEachIndexed { index, item ->
            NavigationDrawerItem(label = {
                Text(text = item.title)
            }, selected = selectedItemIndex == index, onClick = {
                Log.e("clickIndex", "ModalDrawer $index")
                clickIndex(index)
            }, icon = {
                Icon(
                    imageVector = if (index == selectedItemIndex) {
                        item.icon
                    } else item.icon, contentDescription = item.title
                )
            }, badge = {
                /* item.badgeCount?.let {
                     Text(text = item.badgeCount.toString())
                 }*/
            }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
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
) {
    navController.navigate(route) {
        //popUpTo(navController.graph.findStartDestination().id)
       // launchSingleTop = true
    }
    closeDrawer(drawerState = drawerState, scope = scope)
}

@OptIn(ExperimentalMaterial3Api::class)
fun closeDrawer(drawerState: DrawerState, scope: CoroutineScope) {
    scope.launch {
        drawerState.close()
    }
}



