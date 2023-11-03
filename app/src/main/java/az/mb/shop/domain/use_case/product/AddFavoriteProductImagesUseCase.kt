package az.mb.shop.domain.use_case.product

import android.util.Log
import az.mb.shop.common.Resource
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.data.local.entity.FavProductImageEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlin.jvm.Throws

class AddFavoriteProductImagesUseCase(
    private val repository: FavoriteProductRepository
) {
    @Throws()
    suspend operator fun invoke(images: List<FavProductImageEntity>) {
        return repository.addFavoriteProductImages(images = images)
    }
}