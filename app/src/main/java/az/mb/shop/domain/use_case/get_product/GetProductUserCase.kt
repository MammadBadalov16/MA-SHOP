package az.mb.shop.domain.use_case.get_product

import android.util.Log
import az.mb.shop.common.Resource
import az.mb.shop.data.mapper.toProduct
import az.mb.shop.data.repository.ApiRepositoryImpl
import az.mb.shop.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val apiRepositoryImpl: ApiRepositoryImpl) {

    operator fun invoke(id: Int): Flow<Resource<Product>> = channelFlow {

        try {
            send(Resource.Loading())
            val product = apiRepositoryImpl.getProduct(id).toProduct()
            send(Resource.Success(product))
        } catch (e: Exception) {
            send(Resource.Error(e.toString()))
        }
    }
}