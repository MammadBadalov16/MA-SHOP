package az.mb.shop.domain.use_case.product

import az.mb.shop.data.local.entity.favProduct.FavProductImageEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlin.jvm.Throws

class AddFavoriteProductImagesUseCase(
    private val repository: FavoriteProductRepository
) {
    @Throws()
    suspend operator fun invoke(images: List<FavProductImageEntity>) {
        return repository.addFavoriteProductImages(images = images)
    }
}