package az.mb.shop.domain.repository

import az.mb.shop.data.local.entity.cart.CartAboutEntity
import az.mb.shop.data.local.entity.cart.CartEntity
import az.mb.shop.data.local.entity.cart.CartImageEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCarts(): Flow<List<CartEntity>>

    suspend fun addCart(cartAboutEntity: CartAboutEntity)

    suspend fun addCartImages(cartImageEntity: List<CartImageEntity>)

}