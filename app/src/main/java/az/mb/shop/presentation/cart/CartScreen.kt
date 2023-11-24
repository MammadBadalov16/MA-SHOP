package az.mb.shop.presentation.cart

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                CartSection(carts = carts, viewModel = viewModel)

            }

        } else {
            Text(
                text = "Cart is empty",
                overflow = TextOverflow.Ellipsis,
                color = Color.Black

            )

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartSection(carts: List<Cart>, viewModel: CartViewModel) {

    //  val mutableCarts = remember { carts.toMutableList() }

    LazyColumn(state = rememberLazyListState()) {
        itemsIndexed(items = carts, key = { _, item ->
            item.hashCode()
        }) { _, item ->

            val state = rememberDismissState(
                confirmValueChange = {
                    if (it == DismissValue.DismissedToStart) {
                        viewModel.deleteCart(item.cartId)
                        false
                    } else true
                }
            )

            SwipeToDismiss(
                directions = setOf(DismissDirection.EndToStart),
                state = state,
                background = {
                    val color = if (state.dismissDirection == DismissDirection.EndToStart) Color.Red
                    else Color.Transparent

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = color)
                            .padding(horizontal = 20.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete, contentDescription = "Delete",
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }

                },
                dismissContent = {
                    CartItem(cart = item, onClickBuy = {})
                })
        }
    }

}
