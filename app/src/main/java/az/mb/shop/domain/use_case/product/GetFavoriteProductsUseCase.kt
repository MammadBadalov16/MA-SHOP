package az.mb.shop.domain.use_case.product

import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import az.mb.shop.common.Resource
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.data.mapper.toProduct
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GetFavoriteProductsUseCase(private val repository: FavoriteProductRepository) {
    operator fun invoke(): Flow<Resource<Flow<List<FavProductEntity>>>> = flow {
        try {
            emit(Resource.Loading())
            val products = repository.getFavoriteProducts()
            emit(Resource.Success(products))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: "Unknown error"))

        }

    }
}
