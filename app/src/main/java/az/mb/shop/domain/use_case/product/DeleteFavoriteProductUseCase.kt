package az.mb.shop.domain.use_case.product

import az.mb.shop.common.Resource
import az.mb.shop.data.local.entity.FavProductAboutEntity
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class DeleteFavoriteProductUseCase(private val repository: FavoriteProductRepository) {

    suspend operator fun invoke(product: FavProductAboutEntity){
        repository.deleteFavoriteProduct(product = product)
    }
}