package az.mb.shop.domain.use_case.product

import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetFavoriteProductsUseCase(private val repository: FavoriteProductRepository) {

    /*operator fun invoke(): Flow<List<Product>> {

        return repository.getFavoriteProducts().map {  }
    }*/
}