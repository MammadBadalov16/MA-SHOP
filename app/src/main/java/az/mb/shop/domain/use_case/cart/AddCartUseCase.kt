package az.mb.shop.domain.use_case.cart


import az.mb.shop.data.local.entity.CartEntity
import az.mb.shop.data.mapper.toCartEntity
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.CartRepository

class AddCartUseCase(private val repository: CartRepository) {

    suspend operator fun invoke(product: Product, quantity: Int) {

        val cartEntity: CartEntity = product.toCartEntity()
        cartEntity.quantity = quantity

        repository.addCart(cartEntity)
    }
}