package az.mb.shop.domain.repository

import az.mb.shop.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteProductRepository {

    suspend fun getFavoriteProducts(): Flow<List<ProductEntity>>

    suspend fun getFavoriteProductById(id: Int): ProductEntity?

    suspend fun insertFavoriteProduct(productEntity: ProductEntity)

    suspend fun deleteFavoriteProduct(productEntity: ProductEntity)

}