package az.mb.shop.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.mb.shop.domain.model.Cart
import az.mb.shop.domain.model.Product
import az.mb.shop.presentation.components.MyProgressBar
import az.mb.shop.presentation.product.QuantitySection
import az.mb.shop.presentation.ui.theme.f5
import coil.compose.SubcomposeAsyncImage
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun CartItem(
    cart: Cart,
    onClickBuy: (id: Int) -> Unit
) {

    var rating: Float by remember { mutableFloatStateOf(cart.rating.toFloat() / 10) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(120.dp)
            .padding(top = 25.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(f5, shape = RoundedCornerShape(15.dp))
                .clip(RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center,
        ) {
            SubcomposeAsyncImage(model = cart.thumbnail,
                contentDescription = null,
                loading = { MyProgressBar() })
        }

        Spacer(modifier = Modifier.width(20.dp))


        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Column(modifier = Modifier.weight(2.2f)) {
                    Text(
                        text = cart.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Quantity : ${cart.quantity}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }


                Box(modifier = Modifier.weight(0.8f)) {

                    Text(
                        text = "${cart.stock} stock",
                        modifier = Modifier
                            .background(color = f5, shape = RoundedCornerShape(8.dp))
                            .padding(top = 5.dp, bottom = 5.dp, start = 8.dp, end = 8.dp),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }



            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Column(Modifier.weight(1f)) {

                    Text(
                        text = "$${cart.price * cart.quantity!!}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }


                Box(Modifier.weight(2f)) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { onClickBuy(cart.id) },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Black
                        )
                    ) {

                        Text(
                            text = "Confirm",
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }
                }
            }

        }
    }
}





