package az.mb.shop.presentation.home.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import az.mb.shop.R
import az.mb.shop.domain.model.Product
import az.mb.shop.presentation.ui.theme.f3
import coil.compose.AsyncImage
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CartItem2(product: Product) {

    var rating: Float by remember { mutableFloatStateOf(product.rating.toFloat() / 10) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (icon, image) = createRefs()
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(f3, shape = RoundedCornerShape(15.dp))
                    .clip(RoundedCornerShape(15.dp))
                    .clickable {
                        //onItemClick(state.product[it].id)
                    }
                    .constrainAs(image) {},
                contentAlignment = Alignment.Center,

                ) {
                AsyncImage(
                    model = product.images[0],
                    contentDescription = null,
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .padding(3.dp)
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                    },
            )

        }

        Text(
            text = product.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(

                value = rating,
                style = RatingBarStyle.Default,
                numOfStars = 1,
                size = 20.dp,
                onValueChange = {
                    rating = it
                },
                onRatingChanged = {
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "${product.rating} | ", fontSize = 12.sp)
            Text(
                text = "${product.stock} stock",
                modifier = Modifier
                    .background(color = f3, shape = RoundedCornerShape(10.dp))
                    .padding(5.dp),
                fontSize = 10.sp,
                fontWeight = FontWeight.Black
            )
        }

        Text(
            text = "$${product.price}",
            fontWeight = FontWeight(600),
            color = Color.Black
        )

    }
}


