package az.mb.shop.domain.use_case.product

import android.util.Log
import az.mb.shop.data.local.entity.FavProductAboutEntity
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.domain.repository.FavoriteProductRepository
import kotlin.jvm.Throws

class AddFavoriteProductUseCase(
    private val repository: FavoriteProductRepository
) {
    @Throws()
    suspend operator fun invoke(product: FavProductAboutEntity) {

       // Log.e("invoke", productEntity.toString())
       // if (productEntity.pr.id == null)
          //  throw Exception("Product id must be entered")

        repository.addFavoriteProduct(product = product)
    }


}