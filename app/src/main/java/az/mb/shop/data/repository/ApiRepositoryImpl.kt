package az.mb.shop.data.repository

import az.mb.shop.data.remote.ShopApi
import az.mb.shop.data.remote.dto.product.ProductDTO
import az.mb.shop.data.remote.dto.product.ProductsDTO
import az.mb.shop.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val shopApi: ShopApi) : ApiRepository {
    override suspend fun getProducts(): ProductsDTO {
        return shopApi.getProducts()
    }

    override suspend fun getProduct(id: Int): ProductDTO {
        return shopApi.getProduct(id)
    }
}