package az.mb.shop.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import az.mb.shop.domain.model.Category

@Composable
fun CategoryItem(
    category: MutableList<Category>,
    activeCategory: (category: String) -> Unit,
) {

    var activeIndex by rememberSaveable { mutableIntStateOf(0) }

    LazyRow() {
        itemsIndexed(category) { index, item ->

            OutlinedButton(
                colors =
                if (activeIndex == index)
                    ButtonDefaults.buttonColors(Color.Black)
                else
                    ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(1.dp, Color.Black),
                onClick = {
                    activeCategory(item.category)
                    activeIndex = index
                }) {
                Text(
                    item.category, color =
                    if (activeIndex == index)
                        Color.White
                    else
                        Color.Black
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}
