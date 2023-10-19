package az.mb.shop.domain.repository

import az.mb.shop.data.remote.dto.CategoryDTO
import az.mb.shop.data.remote.dto.product.ProductDTO
import az.mb.shop.data.remote.dto.product.ProductsDTO

interface RemoteRepository {

    suspend fun getCategories(): CategoryDTO

    suspend fun getProducts(): ProductsDTO

    suspend fun getProduct(id: Int): ProductDTO

    suspend fun getProductsOfCategory(category: String): ProductsDTO
}