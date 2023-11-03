package az.mb.shop.domain.use_case.product

import az.mb.shop.common.Resource
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class DeleteFavoriteProductImagesUseCase(private val repository: FavoriteProductRepository) {

    suspend operator fun invoke(id: Int) {
        repository.deleteFavoriteProductImages(id = id)

    }
}