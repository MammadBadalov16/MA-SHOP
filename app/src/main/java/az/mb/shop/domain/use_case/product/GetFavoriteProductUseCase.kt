package az.mb.shop.domain.use_case.product

import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteProductUseCase(private val repository: FavoriteProductRepository) {

    operator fun invoke(id: Int): Flow<FavProductEntity> {
        return repository.getFavoriteProductById(id = id)
    }

}