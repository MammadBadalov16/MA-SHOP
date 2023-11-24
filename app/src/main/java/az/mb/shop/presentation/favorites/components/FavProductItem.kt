package az.mb.shop.presentation.favorites.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import az.mb.shop.R
import az.mb.shop.domain.model.Product
import az.mb.shop.presentation.components.MyProgressBar
import az.mb.shop.presentation.ui.theme.f3
import az.mb.shop.presentation.ui.theme.f5
import coil.compose.SubcomposeAsyncImage
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun FavProductsItem(
    product: Product,
    isFav: Boolean,
    onClick: (id: Int) -> Unit
) {

    var rating: Float by remember { mutableFloatStateOf(product.rating!!.toFloat() / 10) }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(top = 25.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.Transparent)
            ) { onClick(product.id) }
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(f5, shape = RoundedCornerShape(15.dp))
                .clip(RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center,
        ) {
            SubcomposeAsyncImage(model = product.thumbnail,
                contentDescription = null,
                loading = { MyProgressBar() })
        }

        Spacer(modifier = Modifier.width(20.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            Text(
                text = product.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Text(
                text = product.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black

            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(value = rating,
                        style = RatingBarStyle.Default,
                        numOfStars = 1,
                        size = 18.dp,
                        onValueChange = {
                            rating = it
                        },
                        onRatingChanged = {})
                    Text(
                        text = "${product.rating} |  ",
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "${product.stock} stock",
                        modifier = Modifier
                            .background(color = f5, shape = RoundedCornerShape(8.dp))
                            .padding(top = 5.dp, bottom = 5.dp, start = 8.dp, end = 8.dp),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }

                Text(
                    text = "$${product.price}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color.Black
                )


            }


        }
    }
}






