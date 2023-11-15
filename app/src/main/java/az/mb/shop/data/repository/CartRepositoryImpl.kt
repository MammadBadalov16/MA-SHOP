package az.mb.shop.data.repository

import az.mb.shop.data.local.dao.CartDao
import az.mb.shop.data.local.entity.cart.CartAboutEntity
import az.mb.shop.data.local.entity.cart.CartEntity
import az.mb.shop.data.local.entity.cart.CartImageEntity
import az.mb.shop.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val dao: CartDao
) : CartRepository {
    override fun getCarts(): Flow<List<CartEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun addCart(cartAboutEntity: CartAboutEntity) {
        dao.addCart(cartAboutEntity)
    }

    override suspend fun addCartImages(cartImageEntity: List<CartImageEntity>) {
        dao.addCartImages(cartImageEntity)
    }

}