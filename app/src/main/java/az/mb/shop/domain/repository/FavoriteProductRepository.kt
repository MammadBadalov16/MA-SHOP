package az.mb.shop.domain.repository

import az.mb.shop.data.local.entity.FavProductAboutEntity
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.data.local.entity.FavProductImageEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteProductRepository {

    fun getFavoriteProducts(): Flow<List<FavProductEntity>>
    suspend fun addFavoriteProduct(product: FavProductAboutEntity)
    suspend fun addFavoriteProductImages(images: List<FavProductImageEntity>)
    suspend fun deleteFavoriteProduct(product: FavProductAboutEntity)

}