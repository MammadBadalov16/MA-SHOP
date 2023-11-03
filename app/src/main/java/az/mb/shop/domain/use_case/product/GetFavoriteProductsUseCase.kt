package az.mb.shop.domain.use_case.product

import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteProductsUseCase(private val repository: FavoriteProductRepository) {
    operator fun invoke(): Flow<List<FavProductEntity>> {

        return repository.getFavoriteProducts()

    }
}