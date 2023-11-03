package az.mb.shop.domain.use_case.product

data class ProductUseCase(
    val getFavoriteProducts: GetFavoriteProductsUseCase,
    val getFavoriteProduct: GetFavoriteProductUseCase,
    val deleteFavoriteProduct: DeleteFavoriteProductUseCase,
    val deleteFavoriteProductImages: DeleteFavoriteProductImagesUseCase,
    val addFavoriteProduct: AddFavoriteProductUseCase,
    val addFavProductImages: AddFavoriteProductImagesUseCase,
)