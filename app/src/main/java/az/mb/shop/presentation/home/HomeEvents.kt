package az.mb.shop.presentation.home

import az.mb.shop.domain.model.Product


sealed class HomeEvents {
    data class AddFavProduct(val data: Product) : HomeEvents()
    data class RemoveFavProduct(val id: Int) : HomeEvents()
}
