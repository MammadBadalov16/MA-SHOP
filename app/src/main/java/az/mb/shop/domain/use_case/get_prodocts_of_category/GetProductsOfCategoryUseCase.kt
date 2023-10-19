package az.mb.shop.domain.use_case.get_prodocts_of_category

import android.util.Log
import az.mb.shop.common.Resource
import az.mb.shop.data.mapper.toProduct
import az.mb.shop.data.mapper.toProducts
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.lang.Exception
import javax.inject.Inject

class GetProductsOfCategoryUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {


    operator fun invoke(category: String): Flow<Resource<List<Product>>> = channelFlow {
        try {
            send(Resource.Loading())
            Log.e("MMM", remoteRepository.getProductsOfCategory("smartphones").toString())
            val products = remoteRepository.getProductsOfCategory(category).products.map { it.toProduct() }
            send(Resource.Success(data = products))
        } catch (e: Exception) {
            send(Resource.Error(e.message.toString()))
        }
    }
}