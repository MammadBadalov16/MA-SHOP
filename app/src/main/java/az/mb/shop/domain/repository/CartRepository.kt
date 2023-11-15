package az.mb.shop.domain.repository

import az.mb.shop.data.local.entity.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCarts(): Flow<List<CartEntity>>

    suspend fun addCart(cartEntity: CartEntity)

    suspend fun deleteCart(cartEntity: CartEntity)

}