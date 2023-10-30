package az.mb.shop.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import az.mb.shop.presentation.main.MainScreen

@Composable
fun ProfileScreen() {

    var click by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Text(text = "Profile Screen", modifier = Modifier.clickable {
            click = true
        })
    }

    if (click) {
        // RootNavGraph(navController = navController, Graph.NAV)
        MainScreen()
    }
}