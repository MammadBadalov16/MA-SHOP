package az.mb.shop.data.repository

import az.mb.shop.data.remote.ShopApi
import az.mb.shop.data.remote.dto.ProductsDTO
import az.mb.shop.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val shopApi: ShopApi) : ApiRepository {
    override suspend fun getProducts(): ProductsDTO {
        return shopApi.getProducts()
    }
}