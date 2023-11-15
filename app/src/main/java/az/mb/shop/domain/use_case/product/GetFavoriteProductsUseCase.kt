package az.mb.shop.domain.use_case.product

import az.mb.shop.common.Resource
import az.mb.shop.data.local.entity.favProduct.FavProductEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFavoriteProductsUseCase(private val repository: FavoriteProductRepository) {
    operator fun invoke(): Flow<Resource<Flow<List<FavProductEntity>>>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getFavoriteProducts()
            emit(Resource.Success(products))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: "Unknown error"))

        }

    }
}
