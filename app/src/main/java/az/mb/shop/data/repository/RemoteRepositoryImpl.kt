package az.mb.shop.data.repository

import az.mb.shop.data.remote.ShopApi
import az.mb.shop.data.remote.dto.CategoryDTO
import az.mb.shop.data.remote.dto.product.ProductDTO
import az.mb.shop.data.remote.dto.product.ProductsDTO
import az.mb.shop.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val shopApi: ShopApi) : RemoteRepository {
    override suspend fun getCategories(): CategoryDTO {
        return shopApi.getCategories()
    }

    override suspend fun getProducts(): ProductsDTO {
        return shopApi.getProducts()
    }

    override suspend fun getSearchProducts(query: String): ProductsDTO {
        return shopApi.getSearchProducts(query = query)
    }

    override suspend fun getProduct(id: Int): ProductDTO {
        return shopApi.getProduct(id)
    }

    override suspend fun getProductsOfCategory(category: String): ProductsDTO {
        return shopApi.getProductsOfCategory(category)
    }
}