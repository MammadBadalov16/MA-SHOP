package az.mb.shop.data.remote

import az.mb.shop.data.remote.dto.CategoryDTO
import az.mb.shop.data.remote.dto.product.ProductDTO
import az.mb.shop.data.remote.dto.product.ProductsDTO
import az.mb.shop.data.remote.dto.search.SearchDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShopApi {

    @GET("products")
    suspend fun getProducts(): ProductsDTO

    @GET("products/category/{category}")
    suspend fun getProductsOfCategory(@Path("category") category: String): ProductsDTO

    @GET("product/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductDTO

    @GET("products/categories")
    suspend fun getCategories(): CategoryDTO

    @GET("products/search")
    suspend fun getSearchProducts(@Query("q") searchKey: String): SearchDTO

}