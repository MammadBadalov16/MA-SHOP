package az.mb.shop.presentation.home.state

import az.mb.shop.data.remote.dto.product.ProductsDTO

data class ProductsState(
    val isLoading: Boolean = false,
    val products: ProductsDTO? = null,
    val error: String = "Unkown"
)
