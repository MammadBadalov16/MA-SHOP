package az.mb.shop.presentation.main

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import az.mb.shop.common.Constants
import az.mb.shop.common.PreferencesManager
import az.mb.shop.navigation.Screen
import az.mb.shop.navigation.graphs.MyNavGraph
import az.mb.shop.navigation.graphs.RootNavGraph
import az.mb.shop.navigation.navigation_items.drawerNavigationScreens
import az.mb.shop.presentation.main.components.BottomNavigationBar
import az.mb.shop.presentation.main.components.ModalDrawer
import az.mb.shop.presentation.main.components.TopBar
import az.mb.shop.presentation.main.components.closeDrawer
import az.mb.shop.presentation.main.components.drawerNavigate
import az.mb.shop.presentation.signIn.state.SignInState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    var clickSignOut by rememberSaveable { mutableStateOf(false) }
    val selectedItemId = remember { mutableIntStateOf(3) }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawer(
                selectedItem = selectedItemId,
                clickItemId = { selectedItemId.value = it },
                clickIndex = {
                    when (it) {
                        5 -> {
                            clickSignOut = true
                            return@ModalDrawer
                        }
                    }
                    if (it != 4)
                        drawerNavigate(
                            navController = navController,
                            route = drawerNavigationScreens[it].route,
                            scope = scope,
                            drawerState = drawerState,
                        )
                })
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
            },
            bottomBar = {
                BottomNavigationBar(
                    navController = navController,
                    clickItemId = { selectedItemId.value = it },
                    selectedItemId = selectedItemId
                )
            },
        )
        {
            it.toString()
            MyNavGraph(navController = navController,drawerState = drawerState)
        }
    }


    if (clickSignOut) {
        PreferencesManager(context = context).removeAllSharedPreferences()
        RootNavGraph(navController = rememberNavController())
    }
}




