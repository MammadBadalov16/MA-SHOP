package az.mb.shop.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun MyProgressBar() {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = Color.Black)
    }
}