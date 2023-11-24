package az.mb.shop.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
fun ProductsItem(
    product: Product,
    isFav: Boolean,
    onClick: (id: Int) -> Unit,
    onClickAddFavorite: (product: Product) -> Unit,
    onClickRemoveFavorite: (product: Product) -> Unit
) {

    var rating: Float by remember { mutableFloatStateOf(product.rating.toFloat() / 10) }
    var favoriteRemember by remember { mutableStateOf(isFav) }

    Box(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.Transparent)
            ) { onClick(product.id!!) }
            .padding(10.dp)
            .wrapContentHeight()
    ) {
        Column() {
            ConstraintLayout() {
                val (icon, image) = createRefs()
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(f5, shape = RoundedCornerShape(15.dp))
                        .clip(RoundedCornerShape(15.dp))
                        .constrainAs(image) {},
                    contentAlignment = Alignment.Center,

                    ) {
                    SubcomposeAsyncImage(
                        model = product.thumbnail,
                        contentDescription = null,
                        loading = { MyProgressBar() }
                    )

                }

                Box(modifier = Modifier
                    .padding(top = 10.dp, end = 10.dp)
                    .size(30.dp)
                    .background(color = Color.Black, shape = CircleShape)
                    .clip(CircleShape)
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.absoluteRight)
                    }
                    .clickable {
                        if (favoriteRemember)
                            onClickRemoveFavorite(product)
                        else
                            onClickAddFavorite(product)
                        favoriteRemember = !favoriteRemember
                    }) {
                    Image(
                        painter = painterResource(
                            id = if (favoriteRemember) R.drawable.ic_favorites_full
                            else R.drawable.ic_favorite
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(20.dp), colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }
            Column() {
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = product.title ?: "Empty",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(
                        value = rating,
                        style = RatingBarStyle.Default,
                        numOfStars = 1,
                        size = 18.dp,
                        onValueChange = {
                            rating = it
                        },
                        onRatingChanged = {
                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "${product.rating} |  ", fontSize = 12.sp, color = Color.Black)
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
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "$${product.price}",
                    fontWeight = FontWeight(600),
                    color = Color.Black
                )
            }
        }
    }
}


