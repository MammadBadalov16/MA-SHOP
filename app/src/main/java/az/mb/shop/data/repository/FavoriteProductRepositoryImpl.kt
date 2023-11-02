package az.mb.shop.data.repository

import az.mb.shop.data.local.dao.FavoriteProductDao
import az.mb.shop.data.local.entity.ProductEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow

class FavoriteProductRepositoryImpl(
    private val dao: FavoriteProductDao
) :
    FavoriteProductRepository {
    override suspend fun getFavoriteProducts(): Flow<List<ProductEntity>> {
        return dao.getFavoriteProducts()
    }

    override suspend fun getFavoriteProductById(favoriteProductId: Int): ProductEntity? {
        return dao.getFavoriteProductById(favoriteProductId)
    }

    override suspend fun insertFavoriteProduct(productEntity: ProductEntity) {
        dao.insertFavoriteProduct(productEntity)
    }

    override suspend fun deleteFavoriteProduct(productEntity: ProductEntity) {
        dao.deleteFavoriteProduct(productEntity )
    }
}