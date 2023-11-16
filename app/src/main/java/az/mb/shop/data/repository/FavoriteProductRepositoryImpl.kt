package az.mb.shop.data.repository

import az.mb.shop.data.local.dao.FavoriteProductDao
import az.mb.shop.data.local.entity.FavProductEntity
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
        return dao.getFavoriteProduct(id = id)
    }

    override suspend fun addFavoriteProduct(favProductEntity: FavProductEntity) {
        dao.addFavoriteProduct(favProductEntity = favProductEntity)
    }

    override suspend fun deleteFavoriteProduct(id: Int) {
        dao.deleteFavoriteProduct(id = id)
    }

}