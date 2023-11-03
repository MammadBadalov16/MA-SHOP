package az.mb.shop.data.repository

import az.mb.shop.data.local.dao.FavoriteProductDao
import az.mb.shop.data.local.entity.FavProductAboutEntity
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.data.local.entity.FavProductImageEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow

class FavoriteProductRepositoryImpl(
    private val dao: FavoriteProductDao
) :
    FavoriteProductRepository {
    override fun getFavoriteProducts(): Flow<List<FavProductEntity>> {
        return dao.getFavoriteProducts()
    }

    override fun getFavoriteProductById(id: Int): Flow<FavProductEntity> {
        return dao.getFavoriteProductById(id = id)
    }

    override suspend fun addFavoriteProduct(product: FavProductAboutEntity) {
        dao.addFavoriteProduct(product)
    }

    override suspend fun addFavoriteProductImages(images: List<FavProductImageEntity>) {
        dao.addFavoriteProductImages(images)

    }

    override suspend fun deleteFavoriteProduct(product: FavProductAboutEntity) {
        dao.deleteFavoriteProduct(product)
    }

    override suspend fun deleteFavoriteProductImages(id: Int) {
        dao.deleteFavoriteProductImages(id)
    }
}