package az.mb.shop.domain.repository

import az.mb.shop.data.local.entity.FavProductEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteProductRepository {

    fun getFavoriteProducts(): Flow<List<FavProductEntity>>
    fun getFavoriteProductById(id: Int): Flow<FavProductEntity>
    suspend fun addFavoriteProduct(favProductEntity: FavProductEntity)
    suspend fun deleteFavoriteProduct(favProductEntity: FavProductEntity)

}