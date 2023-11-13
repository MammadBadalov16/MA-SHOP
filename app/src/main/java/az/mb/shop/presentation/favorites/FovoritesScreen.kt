package az.mb.shop.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.mb.shop.domain.model.Product
import az.mb.shop.presentation.components.ErrorScreen
import az.mb.shop.presentation.favorites.components.FavProductsItem
import az.mb.shop.presentation.home.navigateToProductById

@Composable
fun FavoritesScreen(
    navController: NavController, viewModel: FavoritesViewModel = hiltViewModel()
) {

    val state = viewModel.stateFavProducts
    val products = state.value.products

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {


        if (state.value.isLoading) CircularProgressIndicator(color = Color.Black)

        if (products.isNotEmpty())
            content(products = products, navController = navController)

        if (products.isEmpty() && !state.value.isLoading) Text(text = "Favorites is empty")

        if (state.value.error.isNotBlank())
            ErrorScreen(
                error = state.value.error,
                onClick = { viewModel.getFavProducts() })

    }
}


@Composable
fun content(
    products: List<Product>, navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Favorites",
            overflow = TextOverflow.Ellipsis,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn() {
            items(products) {
                FavProductsItem(
                    product = it,
                    isFav = true,
                    onClick = { id -> navigateToProductById(navController, id) },
                )
            }
        }


    }

}