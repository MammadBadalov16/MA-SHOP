package az.mb.shop.domain.repository

import az.mb.shop.data.remote.dto.ProductsDTO
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getProducts(): ProductsDTO
}