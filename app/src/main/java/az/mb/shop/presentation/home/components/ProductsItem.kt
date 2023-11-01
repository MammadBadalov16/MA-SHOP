package az.mb.shop.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import az.mb.shop.R
import az.mb.shop.domain.model.Product
import az.mb.shop.presentation.components.MyProgressBar
import az.mb.shop.presentation.ui.theme.de
import az.mb.shop.presentation.ui.theme.f3
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductsItem(product: Product, onClick: (id: Int) -> Unit) {

    var rating: Float by remember { mutableFloatStateOf(product.rating.toFloat() / 10) }

    Box(
        modifier = Modifier
            .clickable { onClick(product.id) }
            .padding(10.dp)
    ) {
        Column() {
            ConstraintLayout() {
                val (icon, image) = createRefs()
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(f3, shape = RoundedCornerShape(15.dp))
                        .clip(RoundedCornerShape(15.dp))
                        .constrainAs(image) {},
                    contentAlignment = Alignment.Center,

                    ) {
                    SubcomposeAsyncImage(
                        model = product.images[0],
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
                    .clickable { }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_favorite),
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
                    text = product.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
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
                    Text(text = "${product.rating} |  ", fontSize = 12.sp)
                    Text(
                        text = "${product.stock} stock",
                        modifier = Modifier
                            .background(color = f3, shape = RoundedCornerShape(8.dp))
                            .padding(top = 5.dp, bottom = 5.dp, start = 8.dp, end = 8.dp),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
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


