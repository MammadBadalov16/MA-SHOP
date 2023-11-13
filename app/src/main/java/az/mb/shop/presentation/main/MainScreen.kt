package az.mb.shop.presentation.main

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import az.mb.shop.common.PreferencesManager
import az.mb.shop.navigation.Screen
import az.mb.shop.navigation.graphs.AuthGraph
import az.mb.shop.navigation.graphs.MyNavGraph
import az.mb.shop.presentation.main.components.BottomNavigationBar
import az.mb.shop.presentation.main.components.ModalDrawer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute = navBackStackEntry?.destination?.route


    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val selectedItemId = remember { mutableIntStateOf(3) }


    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawer(
                clickItemId = { selectedItemId.value = it },
                navController = navController,
                drawerState = drawerState,
                scope = scope,
                context = context
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = true,//currentRoute != "product_screen/{productId}",
                    enter = EnterTransition.None
                ) {
                    BottomNavigationBar(
                        navController = navController,
                        clickItemId = { selectedItemId.value = it },
                        selectedItemId = selectedItemId
                    )
                }
            },
        )
        {
            Box(modifier = Modifier.padding(it)) {
                MyNavGraph(
                    navController = navController,
                    drawerState = drawerState
                )
            }
        }
    }

    if (selectedItemId.value == Screen.SignOut.id) {
        navController.popBackStack()
        PreferencesManager(context = context).removeAllSharedPreferences()
        AuthGraph(navController = rememberNavController())
    }
}




