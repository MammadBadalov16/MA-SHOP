package az.mb.shop.presentation.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import az.mb.shop.navigation.SetupHomeNavGraph
import az.mb.shop.presentation.main.components.BottomNavigationBar
import az.mb.shop.presentation.main.components.TopBar
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val scaffoldState = rememberScrollState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    var bnSelectedItemIndex by rememberSaveable { mutableIntStateOf(0) }


    data class NavigationItem(
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val badgeCount: Int? = null
    )

    val items = listOf(
        NavigationItem(
            title = "All",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavigationItem(
            title = "Urgent",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            badgeCount = 45
        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
    )


    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.title)
                        },
                        selected = index == selectedItemIndex,
                        onClick = {

//                          navController.navigate(item.route)
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopBar(drawerState)
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            },
        )
        {
            it.toString()
            SetupHomeNavGraph(navController = navController)
        }
    }

}