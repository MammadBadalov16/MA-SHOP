package az.mb.shop.domain.use_case.product

import android.util.Log
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.data.local.entity.FavProductImageEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlin.jvm.Throws

class AddFavoriteProductImagesUseCase(
    private val repository: FavoriteProductRepository
) {
    @Throws()
    suspend operator fun invoke(images: List<FavProductImageEntity>) {
        repository.addFavoriteProductImages(images = images)
    }
}