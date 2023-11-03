package az.mb.shop.domain.use_case.product

import az.mb.shop.domain.repository.FavoriteProductRepository

class GetFavoriteProductUseCase(private val repository: FavoriteProductRepository) {

    /*suspend operator fun invoke(id: Int): ProductEntity? {
        return repository.getFavoriteProductById(id = id)
    }*/

}