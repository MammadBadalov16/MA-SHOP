package az.mb.shop.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.mb.shop.domain.model.Category

@Composable
fun Category(category: List<Category>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(category) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp)
                    .clickable { }
            ) {
                Box(
                    modifier = Modifier
                        .background(it.color ?: Color.DarkGray)
                        .padding(5.dp)
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box() {
                            Text(
                                text = it.category,
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                        Box(
                        ) {
                            Icon(
                                painter = painterResource(id = az.mb.shop.R.drawable.ic_cate),
                                contentDescription = "Icon",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}
