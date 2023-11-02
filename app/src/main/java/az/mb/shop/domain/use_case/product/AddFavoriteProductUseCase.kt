package az.mb.shop.domain.use_case.product

import az.mb.shop.data.local.entity.ProductEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlin.jvm.Throws

class AddFavoriteProductUseCase(
    private val repository: FavoriteProductRepository
) {
    @Throws()
    suspend operator fun invoke(productEntity: ProductEntity) {
        if (productEntity.productId != null)
            throw Exception("Product id must be entered")

        repository.insertFavoriteProduct(productEntity)
    }


}