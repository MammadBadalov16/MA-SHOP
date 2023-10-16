package az.mb.shop.data.remote

import az.mb.shop.data.remote.dto.ProductsDTO
import retrofit2.Response
import retrofit2.http.GET

interface ShopApi {

    @GET("products")
    suspend fun getProducts(): ProductsDTO

}