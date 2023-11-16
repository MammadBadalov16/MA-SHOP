package az.mb.shop.presentation.product

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.mb.shop.R
import az.mb.shop.domain.model.Product
import az.mb.shop.presentation.components.ErrorScreen
import az.mb.shop.presentation.components.MyProgressBar
import az.mb.shop.presentation.components.ToastSuccess
import az.mb.shop.presentation.components.ToastSuccessC
import az.mb.shop.presentation.product.components.BackButton
import az.mb.shop.presentation.ui.theme.f5
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    navController: NavController
) {
    val stateProduct = viewModel.stateProduct.value
    val product = stateProduct.product
    val images = stateProduct.product?.images

    val stateFavProduct = viewModel.stateFavProducts.value
    val favProduct = stateFavProduct.product

    Log.e("ProductScreen", stateProduct.toString())

    val animations = listOf(
        R.raw.intro1, R.raw.intro2, R.raw.intro3
    )

    var quantity by remember { mutableIntStateOf(1) }
    val totalPrice by remember { mutableDoubleStateOf(0.0) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center,
    ) {

        if (product != null && images != null) {

            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                    .fillMaxSize()
            ) {
                val pagerState = rememberPagerState(pageCount = images.size)

                Column {

                    HeaderSection(navController = navController, product = product)

                    Spacer(modifier = Modifier.height(10.dp))

                    ImageSection(pagerState = pagerState, product = product)

                    Spacer(modifier = Modifier.height(30.dp))

                    ContentSection(product = product,
                        isFav = favProduct != null,
                        viewModel = viewModel,
                        onClickAddFavorite = {
                            viewModel.onEvent(
                                ProductScreenEvents.AddFavProduct(
                                    it
                                )
                            )
                        },
                        onClickRemoveFavorite = {
                            viewModel.onEvent(
                                ProductScreenEvents.RemoveFavProduct(
                                    it
                                )
                            )
                        }
                    )

                }
            }
        }

        if (stateProduct.error.isNotBlank()) {
            ErrorScreen(error = stateProduct.error, onClick = {})
        }

        if (stateProduct.isLoading) CircularProgressIndicator(color = Color.Black)

    }
}


@Composable
fun HeaderSection(navController: NavController, product: Product) {

    Box(modifier = Modifier.fillMaxWidth()) {
        BackButton(onClick = { navController.navigateUp() })

        Box(modifier = Modifier.align(Alignment.Center)) {
            Text(text = product.brand)
        }
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSection(pagerState: PagerState, product: Product) {

    Box(
        modifier = Modifier
            .width(400.dp)
            .height(400.dp)
            .background(f5, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp)),
        contentAlignment = Alignment.Center,

        ) {

        ConstraintLayout {

            val (indicator, horizontalPager) = createRefs()

            HorizontalPager(state = pagerState,
                modifier = Modifier.constrainAs(horizontalPager) {}) { pagerCount ->

                SubcomposeAsyncImage(modifier = Modifier.fillMaxSize(),
                    model = product.images[pagerCount],
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    loading = {
                        MyProgressBar()
                    })
            }
            PageIndicator(
                pageCount = product.images.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .padding(0.dp)
                    .constrainAs(indicator) {
                        centerHorizontallyTo(parent)
                        bottom.linkTo(horizontalPager.bottom, margin = 10.dp)
                    },
            )
        }
    }

}

@Composable
fun ContentSection(
    product: Product,
    isFav: Boolean,
    viewModel: ProductViewModel,
    onClickAddFavorite: (product: Product) -> Unit,
    onClickRemoveFavorite: (product: Product) -> Unit,
) {

    val context = LocalContext.current.applicationContext
    var rating: Float by remember { mutableFloatStateOf(product.rating.toFloat() / 10) }
    var favoriteRemember by remember { mutableStateOf(isFav) }
    var quantityRemember by remember { mutableIntStateOf(1) }

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = product.title, fontSize = 25.sp, fontWeight = FontWeight.Bold
            )

            Icon(painter = painterResource(
                id = if (!favoriteRemember) R.drawable.ic_heart
                else R.drawable.ic_heart_full
            ),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .size(25.dp)
                    .clickable {
                        if (favoriteRemember) onClickRemoveFavorite(product)
                        else onClickAddFavorite(product)
                        favoriteRemember = !favoriteRemember
                    }

            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${product.stock} stock",
                modifier = Modifier
                    .background(color = f5, shape = RoundedCornerShape(8.dp))
                    .padding(top = 3.dp, bottom = 3.dp, start = 8.dp, end = 8.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Black
            )

            Spacer(modifier = Modifier.width(10.dp))


            RatingBar(value = rating,
                style = RatingBarStyle.Default,
                numOfStars = 1,
                size = 20.dp,
                onValueChange = {
                    rating = it
                },
                onRatingChanged = {})
            Spacer(modifier = Modifier.width(5.dp))

            Text(text = "${product.rating} (5.389 reviews)", fontSize = 17.sp)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Divider(color = Color.LightGray, thickness = 1.dp)

        Spacer(modifier = Modifier.height(15.dp))

        DescriptionSection(product = product)

        Spacer(modifier = Modifier.height(15.dp))

        QuantitySection(quantityValue = { quantity -> quantityRemember = quantity })

        Spacer(modifier = Modifier.height(15.dp))

        PriceAndToCartSection(
            totalPrice = (quantityRemember * product.price).toDouble(),
            addToCart = {
                viewModel.onEvent(
                    ProductScreenEvents.AddToCart(
                        data = product,
                        quantity = quantityRemember
                    )
                )
                ToastSuccess(context =context, message = "Added successfully !")
            }
        )
    }
}

@Composable
fun DescriptionSection(product: Product) {

    Column {
        Text(text = "Description", fontSize = 22.sp, fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.description,
            fontSize = 15.sp,
            style = LocalTextStyle.current.copy(lineHeight = 18.sp)
        )
    }


}

@Composable
fun QuantitySection(quantityValue: (quantity: Int) -> Unit) {

    var quantityRemember by remember { mutableIntStateOf(1) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Quantity",
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.width(10.dp))

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

    Spacer(modifier = Modifier.height(15.dp))

    Divider(color = Color.LightGray, thickness = 1.dp)


}

@Composable
fun PriceAndToCartSection(
    totalPrice: Double,
    addToCart: () -> Unit
) {

    Row(Modifier.fillMaxWidth()) {


        Column(Modifier.weight(1f)) {

            Text(
                text = "Total price",
                color = Color.Gray
            )

            Text(
                text = totalPrice.toString(),
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }


        Box(Modifier.weight(2f)) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = { addToCart() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                )
            ) {

                Text(text = "Add to cart")

            }
        }

    }


}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorSingleDot(isSelected = it == currentPage)
        }


    }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {

    val width = animateDpAsState(targetValue = if (isSelected) 35.dp else 10.dp, label = "")
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) Color.Black else Color.LightGray)
    )
}




