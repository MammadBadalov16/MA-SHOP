package az.mb.shop.domain.use_case.product

import az.mb.shop.data.local.entity.ProductEntity
import az.mb.shop.domain.repository.FavoriteProductRepository

class DeleteFavoriteProductUseCase(private val repository: FavoriteProductRepository) {

    suspend operator fun invoke(productEntity: ProductEntity) {
        repository.deleteFavoriteProduct(productEntity = productEntity)
    }
}