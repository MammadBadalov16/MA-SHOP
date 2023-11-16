package az.mb.shop.domain.repository

import androidx.room.Query
import az.mb.shop.data.remote.dto.CategoryDTO
import az.mb.shop.data.remote.dto.product.ProductDTO
import az.mb.shop.data.remote.dto.product.ProductsDTO

interface RemoteRepository {

    suspend fun getCategories(): CategoryDTO

    suspend fun getProducts(): ProductsDTO

    suspend fun getSearchProducts(query: String): ProductsDTO

    suspend fun getProduct(id: Int): ProductDTO

    suspend fun getProductsOfCategory(category: String): ProductsDTO

}