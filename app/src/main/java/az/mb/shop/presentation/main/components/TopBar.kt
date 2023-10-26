package az.mb.shop.presentation.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()


    Button(onClick = {
        scope.launch {
            drawerState.open()
        }
    }) {
        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
    }

}