package az.mb.shop.presentation.home

import az.mb.shop.domain.model.Product


sealed class HomeEvents {
    data class FavProduct(val data: Product) : HomeEvents()
}
