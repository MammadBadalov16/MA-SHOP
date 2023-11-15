package az.mb.shop.domain.use_case.product

import az.mb.shop.data.mapper.toFavProductEntity
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlin.jvm.Throws

class AddFavoriteProductUseCase(
    private val repository: FavoriteProductRepository
) {
    @Throws()
    suspend operator fun invoke(product: Product) {
        repository.addFavoriteProduct(favProductEntity = product.toFavProductEntity())
    }
}