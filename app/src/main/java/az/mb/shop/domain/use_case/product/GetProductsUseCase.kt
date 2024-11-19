package az.mb.shop.domain.use_case.product

import android.util.Log
import az.mb.shop.common.Resource
import az.mb.shop.data.mapper.toProduct
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.lang.Exception
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    operator fun invoke(): Flow<Resource<List<Product>>> = channelFlow {
        try {
            send(Resource.Loading())
            var products = remoteRepository.getProducts().products.map { it.toProduct() }
            send(Resource.Success(products))
        } catch (e: Exception) {
            Log.e("Get products",e.message.toString())
            send(Resource.Error(e.message.toString()))
        }
    }
}