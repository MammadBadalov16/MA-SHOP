package az.mb.shop.presentation.home.state

import az.mb.shop.data.remote.dto.product.ProductsDTO
import az.mb.shop.domain.model.Product

data class ProductsState(
    val isLoading: Boolean = false,
    var products: List<Product> = emptyList(),
    val error: String = ""
)
