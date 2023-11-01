package az.mb.shop.presentation.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import az.mb.shop.presentation.ui.theme.darkGrey
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    Icon(
        imageVector = Icons.Filled.Menu,
        contentDescription = "Menu",

        tint = darkGrey,
        modifier = Modifier
            .size(28.dp)
            .clickable {
                scope.launch {
                    drawerState.open()
                }
            })
}