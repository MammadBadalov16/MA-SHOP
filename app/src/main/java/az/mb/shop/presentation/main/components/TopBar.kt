package az.mb.shop.presentation.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import az.mb.shop.R
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.mb.shop.presentation.ui.theme.darkGrey
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    Icon(
        painter = painterResource(id = R.drawable.ic_menu),
        contentDescription = "Menu",

        tint = Color.Black,
        modifier = Modifier
            .size(28.dp)
            .clickable {
                scope.launch {
                    drawerState.open()
                }
            })
}