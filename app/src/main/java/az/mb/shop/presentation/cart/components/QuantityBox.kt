package az.mb.shop.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.mb.shop.R
import az.mb.shop.presentation.ui.theme.f5
@Composable
fun Quantity(quantityValue: (quantity: Int) -> Unit) {

    var quantityRemember by remember { mutableIntStateOf(1) }



        Row(
            modifier = Modifier
                .background(color = f5, shape = RoundedCornerShape(30.dp))
                .padding(top = 12.dp, bottom = 12.dp, start = 18.dp, end = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_minus),
                tint = Color.Black,
                contentDescription = "",
                modifier = Modifier.clickable {
                    if (quantityRemember != 1)
                        quantityRemember--
                    quantityValue(quantityRemember)
                }
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                text = quantityRemember.toString(),
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(18.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                tint = Color.Black,
                contentDescription = "",
                modifier = Modifier.clickable {
                    quantityRemember++
                    quantityValue(quantityRemember)
                }
            )
        }
}
