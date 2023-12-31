package az.mb.shop.domain.use_case.product

import az.mb.shop.common.Resource
import az.mb.shop.data.mapper.toProduct
import az.mb.shop.data.repository.RemoteRepositoryImpl
import az.mb.shop.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.lang.Exception
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val remoteRepositoryImpl: RemoteRepositoryImpl) {

    operator fun invoke(id: Int): Flow<Resource<Product>> = channelFlow {

        try {
            send(Resource.Loading())
            val product = remoteRepositoryImpl.getProduct(id).toProduct()
            send(Resource.Success(product))
        } catch (e: Exception) {
            send(Resource.Error(e.toString()))
        }
    }
}