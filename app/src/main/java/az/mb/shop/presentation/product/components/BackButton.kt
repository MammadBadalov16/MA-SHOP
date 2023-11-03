package az.mb.shop.presentation.product.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import az.mb.shop.R
import az.mb.shop.presentation.ui.theme.f3
import az.mb.shop.presentation.ui.theme.f5

@Composable
fun BackButton(onClick: () -> Unit) {
    Box(modifier = Modifier
        .size(30.dp)
        .background(color = Color.Transparent, shape = CircleShape)
        .clip(CircleShape)
        .clickable { onClick() }, contentAlignment = Alignment.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp), colorFilter = ColorFilter.tint(Color.Black)
        )
    }
}
