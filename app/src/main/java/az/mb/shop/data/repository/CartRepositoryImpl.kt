package az.mb.shop.data.repository

import az.mb.shop.data.local.dao.CartDao
import az.mb.shop.data.local.entity.CartEntity
import az.mb.shop.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

class CartRepositoryImpl(
    private val dao: CartDao
) : CartRepository {
    override fun getCarts(): Flow<List<CartEntity>> {
        return dao.getCarts()
    }

    override suspend fun insertCart(cartEntity: CartEntity) {
        return dao.insertCart(cartEntity = cartEntity)
    }

    override suspend fun deleteCart(cartEntity: CartEntity) {
        dao.deleteCart(cartEntity = cartEntity)
    }
}