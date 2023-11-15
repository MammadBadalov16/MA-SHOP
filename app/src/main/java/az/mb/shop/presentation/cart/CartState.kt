package az.mb.shop.presentation.cart

import az.mb.shop.domain.model.Cart
import az.mb.shop.domain.model.Product

data class CartState(
    val isLoading: Boolean = false,
    val cart: List<Cart>? = null,
    val error: String = ""

)
