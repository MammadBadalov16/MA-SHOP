package az.mb.shop.presentation.home.state

import az.mb.shop.domain.model.Product

data class ProductState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String = ""

)
