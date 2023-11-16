package az.mb.shop.domain.use_case.product

import az.mb.shop.common.Resource
import az.mb.shop.data.mapper.toProduct
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.lang.Exception
import javax.inject.Inject

class GetSearchProductsUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    operator fun invoke(query: String): Flow<Resource<List<Product>>> = channelFlow {
        try {
            send(Resource.Loading())
            var products =
                remoteRepository.getSearchProducts(query = query).products.map { it.toProduct() }
            send(Resource.Success(products))
        } catch (e: Exception) {
            send(Resource.Error(e.message.toString()))
        }
    }
}