package az.mb.shop.domain.repository

import az.mb.shop.data.remote.dto.product.ProductDTO
import az.mb.shop.data.remote.dto.product.ProductsDTO

interface ApiRepository {

    suspend fun getProducts(): ProductsDTO

    suspend fun getProduct(id: Int): ProductDTO
}