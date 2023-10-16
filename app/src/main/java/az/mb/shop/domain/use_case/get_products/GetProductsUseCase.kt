package az.mb.shop.domain.use_case.get_products

import az.mb.shop.common.Resource
import az.mb.shop.data.remote.dto.ProductsDTO
import az.mb.shop.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val apiRepository: ApiRepository) {

    operator fun invoke(): Flow<Resource<ProductsDTO>> = flow {
        try {
            emit(Resource.Loading())
            var products = apiRepository.getProducts()
            emit(Resource.Success(products))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.toString()))
        }
    }
}