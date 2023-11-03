package az.mb.shop.domain.use_case.product

import android.util.Log
import az.mb.shop.common.Resource
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList

class GetFavoriteProductsUseCase(private val repository: FavoriteProductRepository) {
    operator fun invoke(): Flow<List<FavProductEntity>> {
        return repository.getFavoriteProducts()
    }
}
