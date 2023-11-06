package az.mb.shop.domain.use_case.cart

import az.mb.shop.data.local.entity.CartEntity
import az.mb.shop.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartUseCase (private val repository: CartRepository) {

    operator fun invoke(): Flow<List<CartEntity>> {
        return repository.getCarts()
    }
}