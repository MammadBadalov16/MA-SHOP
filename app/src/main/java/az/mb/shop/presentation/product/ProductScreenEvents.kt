package az.mb.shop.presentation.product

import az.mb.shop.domain.model.Product


sealed class ProductScreenEvents {
    data class AddFavProduct(val data: Product) : ProductScreenEvents()
    data class RemoveFavProduct(val data: Product) : ProductScreenEvents()
    data class AddToCart(val data: Product, val quantity: Int) : ProductScreenEvents()
}
