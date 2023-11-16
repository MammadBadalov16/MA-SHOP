package az.mb.shop.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import az.mb.shop.domain.model.Cart
import az.mb.shop.presentation.cart.components.CartItem

@Composable
fun CartScreen(viewModel: CartViewModel = hiltViewModel()) {
    val carts = viewModel.stateCart.value.cart


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        if (!carts.isNullOrEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Text(
                    text = "Cart",
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(12.dp))

                CartSection(carts = carts)

            }

        }

    }
}

@Composable
fun CartSection(carts: List<Cart>) {

    LazyColumn {
        items(
            count = carts.size,
            itemContent = {
                CartItem(cart = carts[it], onClickBuy = {})
            }
        )

    }

}