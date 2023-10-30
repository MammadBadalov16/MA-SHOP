package az.mb.shop.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import az.mb.shop.R

@Composable
fun CartItem() {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp),
        modifier = Modifier.height(150.dp)
    ) {

        item {
            ConstraintLayout(
                modifier = Modifier
                    .width(280.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                //constrains
                val (bannerText, bannerImage) = createRefs()

                Image(
                    painter = painterResource(id = R.drawable.img1),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.constrainAs(bannerImage) {}
                )

                Column(
                    modifier = Modifier
                        .background(Color(0x8DB3B0B0))
                        .padding(15.dp)
                        .constrainAs(bannerText) {
                            top.linkTo(bannerImage.top)
                            bottom.linkTo(bannerImage.bottom)
                            start.linkTo(bannerImage.start)
                            end.linkTo(bannerImage.end)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                ) {
                    Text(
                        text = "Fashion",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.heightIn(15.dp))
                    Text(text = "85 Brands", color = Color.White)
                }
            }
        }
    }

}