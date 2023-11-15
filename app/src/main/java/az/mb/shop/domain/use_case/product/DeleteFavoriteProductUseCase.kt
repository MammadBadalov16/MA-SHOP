package az.mb.shop.domain.use_case.product

import az.mb.shop.data.mapper.toFavProductEntity
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.FavoriteProductRepository

class DeleteFavoriteProductUseCase(private val repository: FavoriteProductRepository) {

    suspend operator fun invoke(product: Product){
        repository.deleteFavoriteProduct(favProductEntity = product.toFavProductEntity())
    }
}