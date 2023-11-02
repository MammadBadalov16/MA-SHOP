package az.mb.shop.domain.use_case.product

import az.mb.shop.data.mapper.toProduct
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.FavoriteProductRepository

class GetFavoriteProductUseCase(private val repository: FavoriteProductRepository) {

    suspend operator fun invoke(id: Int): Product? {
        return repository.getFavoriteProductById(id = id)?.toProduct()
    }

}