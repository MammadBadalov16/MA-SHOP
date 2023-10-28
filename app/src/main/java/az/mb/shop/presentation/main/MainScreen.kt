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

    val context = LocalContext.current.applicationContext
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var isClicked by rememberSaveable { mutableStateOf(false) }
    var clickIndex by rememberSaveable { mutableIntStateOf(0) }


    val navController = rememberNavController()
    val scope = rememberCoroutineScope()


    if (clickIndex != 2) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawer(clickIndex = {
                    clickIndex = it
                    when (clickIndex) {
                        2, 3 -> {
                            drawerNavigate(
                                navController = navController,
                                route = drawerNavigationScreens[clickIndex].route,
                                scope = scope,
                                drawerState = drawerState,
                            )
                        }

                        1 -> {
                            isClicked = true
                        }
                    }
                })
            },
            drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    TopBar(drawerState)
                },
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        clickedProfile = { clickIndex = 2 })
                },
            )
            {
                it.toString()
                MyNavGraph(navController = navController)
            }
        }
    }

    Log.e("index", clickIndex.toString())
    Log.e("isClicked", isClicked.toString())
    // if (isClicked) {
    when (clickIndex) {
        1 -> {
            closeDrawer(drawerState = drawerState, scope = scope)
            navController.navigate(Screen.Profile.route)
            // MyNavGraph(navController = navController, startDestination = Screen.Profile.route)
            Log.e("clickIndex", "1")
        }

        5 -> {
            PreferencesManager(context = context).deleteData(Constants.TOKEN)
            RootNavGraph(navController = rememberNavController())
        }

        else -> {}
    }
    // }
}



