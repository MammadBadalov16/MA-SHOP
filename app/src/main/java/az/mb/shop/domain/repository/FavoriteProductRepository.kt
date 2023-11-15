package az.mb.shop.domain.repository

import az.mb.shop.data.local.entity.favProduct.FavProductAboutEntity
import az.mb.shop.data.local.entity.favProduct.FavProductEntity
import az.mb.shop.data.local.entity.favProduct.FavProductImageEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteProductRepository {

    fun getFavoriteProducts(): Flow<List<FavProductEntity>>
    fun getFavoriteProductById(id: Int): Flow<FavProductEntity>
    suspend fun addFavoriteProduct(product: FavProductAboutEntity)
    suspend fun addFavoriteProductImages(images: List<FavProductImageEntity>)
    suspend fun deleteFavoriteProduct(product: FavProductAboutEntity)
    suspend fun deleteFavoriteProductImages(id: Int)

}