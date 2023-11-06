package az.mb.shop.domain.use_case.cart

import az.mb.shop.data.local.entity.CartEntity
import az.mb.shop.domain.repository.CartRepository
import javax.inject.Inject

class AddCartUseCase (private val repository: CartRepository) {

    suspend operator fun invoke(cartEntity: CartEntity) {
        repository.insertCart(cartEntity = cartEntity)
    }

}