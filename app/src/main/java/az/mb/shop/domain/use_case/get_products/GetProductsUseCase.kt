package az.mb.shop.domain.use_case.get_products

import az.mb.shop.common.Resource
import az.mb.shop.data.remote.dto.product.ProductsDTO
import az.mb.shop.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val apiRepository: ApiRepository) {

    operator fun invoke(): Flow<Resource<ProductsDTO>> = channelFlow  {
        try {
            send(Resource.Loading())
            var products = apiRepository.getProducts()
            send(Resource.Success(products))
        } catch (exception: Exception) {
            send(Resource.Error(exception.message.toString()))
        }
    }
}