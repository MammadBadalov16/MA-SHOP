package az.mb.shop.domain.use_case.product

import az.mb.shop.data.local.entity.favProduct.FavProductAboutEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlin.jvm.Throws

class AddFavoriteProductUseCase(
    private val repository: FavoriteProductRepository
) {
    @Throws()
    suspend operator fun invoke(product: FavProductAboutEntity) {
        repository.addFavoriteProduct(product = product)
    }
}