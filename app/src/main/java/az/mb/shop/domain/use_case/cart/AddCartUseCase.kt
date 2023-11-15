package az.mb.shop.domain.use_case.cart

import az.mb.shop.data.local.entity.cart.CartEntity
import az.mb.shop.data.mapper.toCartAboutEntity
import az.mb.shop.data.mapper.toListCartImageEntity
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.CartRepository

class AddCartUseCase(private val repository: CartRepository) {

    suspend operator fun invoke(product: Product) {
        repository.addCart(product.toCartAboutEntity())
        repository.addCartImages(product.toListCartImageEntity())
    }
}